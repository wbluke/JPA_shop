package study.api.test.ch5;

import org.junit.jupiter.api.*;
import study.api.test.model.Member;
import study.api.test.model.Team;

import javax.persistence.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class BasicRelationMappingTest {
    private static EntityManagerFactory emf;

    private EntityManager entityManager;
    private EntityManager anotherEntityManager;
    private Member member1;
    private Member member2;
    private Team team1;
    private Team team2;

    @BeforeAll
    static void setUpBeforeAll() {
        emf = Persistence.createEntityManagerFactory("jpabook");
    }

    @BeforeEach
    void setUp() {
        entityManager = emf.createEntityManager();
        anotherEntityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        member1 = new Member("member1");
        member2 = new Member("member2");
        team1 = new Team("team1");
        team2 = new Team("team2");
    }

    @Test
    @DisplayName("연관 관계를 가진 엔티티들을 저장한다.")
    void saveRelatedEntities() {
        // Given
        Long id1;
        Long id2;
        EntityTransaction transaction = entityManager.getTransaction();

        // When
        transaction.begin();

        entityManager.persist(team1);
        member1.setTeam(team1);
        entityManager.persist(member1);
        id1 = member1.getId();

        member2.setTeam(team1);
        entityManager.persist(member2);
        id2 = member2.getId();

        transaction.commit();

        // Then
        Member savedMember1 = anotherEntityManager.find(Member.class, id1);
        Member savedMember2 = anotherEntityManager.find(Member.class, id2);

        assertThat(savedMember1.getTeam()).isEqualTo(team1);
        assertThat(savedMember2.getTeam()).isEqualTo(team1);
    }

    @Test
    @DisplayName("연관된 모든 엔티티가 영속 상태가 아니면 엔티티들을 저장할 수 없다.")
    void saveFailIfRelatedEntityIsNotInPersistenceContext() {
        EntityTransaction transaction = entityManager.getTransaction();

        assertThatCode(() -> {
            transaction.begin();
            member1.setTeam(team1);
            entityManager.persist(member1);
            transaction.commit();
        }).isInstanceOf(RollbackException.class);
    }

    @Test
    @DisplayName("JPQL로 저장된 entity들을 조회한다.")
    void jpqlSelectWithJoin() {
        // Given
        EntityTransaction transaction = entityManager.getTransaction();
        Team team3 = new Team("team3");

        transaction.begin();

        entityManager.persist(team3);
        member1.setTeam(team3);
        member2.setTeam(team3);
        entityManager.persist(member1);
        entityManager.persist(member2);

        transaction.commit();

        // When
        String jpql = "select m from Member m join m.team t where t.name = :teamName";

        // Then
        List<Member> fetchedMemebers = entityManager.createQuery(jpql, Member.class)
                .setParameter("teamName", "team3")
                .getResultList();

        assertThat(fetchedMemebers.size()).isEqualTo(2);
        assertThat(fetchedMemebers).contains(member1, member2);
    }

    @Test
    @DisplayName("Memeber의 team과의 연관관계를 team1에서 team2로 수정한다.")
    void editRelation() {
        // Given
        Long id;
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.persist(team1);
        member1.setTeam(team1);
        entityManager.persist(member1);
        id = member1.getId();

        transaction.commit();

        // When
        EntityTransaction transaction2 = entityManager.getTransaction();

        transaction2.begin();

        entityManager.persist(team2);
        member1.setTeam(team2);

        transaction2.commit();

        // Then
        assertThat(anotherEntityManager.find(Member.class, id).getTeam()).isEqualTo(team2);
    }

    @Test
    @DisplayName("연관 관계를 먼저 제거하지 않으면 연관된 엔티티를 삭제할 때 데이터베이스 오류가 발생한다.")
    void deleteRelationalEntity() {
        // Given
        EntityTransaction transaction = entityManager.getTransaction();
        Long id;

        transaction.begin();

        entityManager.persist(team1);
        id = team1.getId();
        member1.setTeam(team1);
        entityManager.persist(member1);

        transaction.commit();

        // when
        EntityTransaction transaction2 = anotherEntityManager.getTransaction();
        Team deletingTeam = anotherEntityManager.find(Team.class, id);

        // Then
        assertThatCode(() -> {
            transaction2.begin();
            anotherEntityManager.remove(deletingTeam);
            transaction2.commit();
        }).isInstanceOf(RollbackException.class);
    }

    @Test
    @DisplayName("OneToMany 매핑을 통해 Team에서도 Member 객체를 탐색할 수 있다.")
    void biDirectional() {
        // Given
        EntityTransaction transaction = entityManager.getTransaction();
        String jpql = "select m from Member m join m.team t where t.name = :teamName";
        Long id;

        transaction.begin();

        entityManager.persist(team1);
        id = team1.getId();
        member1.setTeam(team1);
        member2.setTeam(team1);
        entityManager.persist(member1);
        entityManager.persist(member2);

        transaction.commit();

        List<Member> expectedMemebers = entityManager.createQuery(jpql, Member.class)
                .setParameter("teamName", "team1")
                .getResultList();

        // When
        Team team = anotherEntityManager.find(Team.class, id);
        List<Member> actualMembers = team.getMembers();

        // Then
        assertThat(actualMembers).containsAll(expectedMemebers);
        assertThat(expectedMemebers).containsAll(actualMembers);
    }

    @Test
    @DisplayName("양방향 연관관계를 설정하고 주인이 아닌 곳에만 값을 입력하면 DB 외래키값이 저장되지 않는다.")
    void saveNonOwnerOnly() {
        // Given
        EntityTransaction transaction = entityManager.getTransaction();
        Long id;

        // When
        transaction.begin();

        entityManager.persist(member1);
        id = member1.getId();

        team1.getMembers().add(member1);
        entityManager.persist(team1);

        transaction.commit();

        // Then
        Member savedMember1 = anotherEntityManager.find(Member.class, id);
        assertThat(savedMember1.getTeam()).isNull();
    }

    @Test
    @DisplayName("양방향 연관 관계를 변경할 때에는 기존 연관관계를 삭제하는 코드를 추가해야 안전하다.")
    void changeBiDirectional() {
        member1.setTeamBiDirectionally(team1);
        member1.setTeamBiDirectionally(team2);

        assertThat(team1.getMembers()).doesNotContain(member1);
        assertThat(team2.getMembers()).contains(member1);
    }

    @AfterEach
    void tearDown() {
        entityManager.close();
        anotherEntityManager.close();
    }

    @AfterAll
    static void tearDownAfterAll() {
        emf.close();
    }
}
