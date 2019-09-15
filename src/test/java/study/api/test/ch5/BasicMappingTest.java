package study.api.test.ch5;

import org.junit.jupiter.api.*;
import study.api.test.model.Team;
import study.api.test.model.User;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BasicMappingTest {
    private static EntityManagerFactory emf;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    private Team team;
    private User user;

    @BeforeAll
    static void setupBeforeAll() {
        emf = Persistence.createEntityManagerFactory("jpabook");
    }

    @BeforeEach
    void setUp() {
        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
        team = new Team("red");
        user = new User("user1");
    }

    @AfterEach
    void tearDown() {
        entityManager.close();
    }

    @AfterAll
    static void tearDownAfterAll() {
        emf.close();
    }

    @Test
    @DisplayName("Team을 영속화하지 않은 상태에서 User 저장하기")
    void persistUserWithoutPersistingTeam() {
        transaction.begin();

        user.setTeam(team);

        entityManager.persist(user);

        assertThrows(RollbackException.class, transaction::commit);
    }

    @Test
    @DisplayName("연관관계가 없어지지 않은 채 삭제 시도")
    void removeWithoutDeletingRelation() {
        transaction.begin();

        entityManager.persist(team);

        user.setTeam(team);
        entityManager.persist(user);

        entityManager.remove(team);

        assertThrows(RollbackException.class, transaction::commit);
    }
}
