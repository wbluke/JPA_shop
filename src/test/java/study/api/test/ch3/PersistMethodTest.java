package study.api.test.ch3;

import org.junit.jupiter.api.*;
import study.api.test.model.ApiTestEntity;

import javax.persistence.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PersistMethodTest {
    private static final String TEST_NAME = "test entity";
    private static final Integer TEST_AGE = 28;
    private static EntityManagerFactory emf;

    private EntityManager entityManager;
    private EntityManager anotherManager;
    private ApiTestEntity apiTestEntity;

    @BeforeAll
    static void setupBeforeAll() {
        emf = Persistence.createEntityManagerFactory("jpabook");
    }

    @BeforeEach
    void setUp() {
        entityManager = emf.createEntityManager();
        anotherManager = emf.createEntityManager();
        apiTestEntity = new ApiTestEntity(TEST_NAME, TEST_AGE);
    }

    @Test
    @DisplayName("엔티티를 영속화하면 영속성 컨텍스트(엔티티 매니저)에 엔티티가 저장된다")
    void persist() {
        entityManager.persist(apiTestEntity);

        assertThat(entityManager.contains(apiTestEntity)).isTrue();
    }

    @Test
    @DisplayName("트렌젝션 없이 엔티티를 persist하면 id가 자동생성되지 않는다.")
    void idNotGenerated() {
        entityManager.persist(apiTestEntity);

        assertThat(apiTestEntity.getId()).isNull();
    }

    @Test
    @DisplayName("트렌젝션이 있을 때 엔티티를 persist하면 commit 하기 전에 id가 자동생성된다.")
    void idGenerated() {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        assertThat(apiTestEntity.getId()).isNull();
        entityManager.persist(apiTestEntity);
        assertThat(apiTestEntity.getId()).isNotNull();
        transaction.rollback();

        // rollback 해도 자동생성된 id는 사라지지 않는다.
        assertThat(apiTestEntity.getId()).isNotNull();
    }

    @Test
    @DisplayName("persist하고 트렌젝션을 rollback하면 엔티티를 1차 캐시에서 지운다.")
    void removeCacheWhenRollback() {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(apiTestEntity);
        transaction.rollback();

        assertThat(entityManager.contains(apiTestEntity)).isFalse();
    }

    @Test
    @DisplayName("id의 Generation Type이 identity이면 persist할 때 id가 자동 생성된다.")
    void persistWhenGenerationTypeIsIdentity() {
        EntityTransaction transaction = entityManager.getTransaction();
        Long id;

        transaction.begin();
        entityManager.persist(apiTestEntity);
        transaction.commit();
        id = apiTestEntity.getId();

        assertThat(id).isNotNull();
    }

    @Test
    @DisplayName("트렌젝션이 없는 경우 Generation Type이 identity이더라도 id가 생성되지 않는다.")
    void persisWhenGenerationTypeIsIdentityWithoutTransaction() {
        Long id;

        entityManager.persist(apiTestEntity);
        id = apiTestEntity.getId();

        assertThat(id).isNull();
    }

    @Test
    @DisplayName("트렌젝션을 시작하고 엔티티를 persist하면 DB에 저장된다.")
    void persistWithTransaction() {
        EntityTransaction transaction = entityManager.getTransaction();
        Long id;

        transaction.begin();
        entityManager.persist(apiTestEntity);
        transaction.commit();

        id = apiTestEntity.getId();

        assertThat(anotherManager.find(ApiTestEntity.class, id)).isEqualTo(apiTestEntity);
    }

    @Test
    @DisplayName("트렌젝션을 시작하지 않고 엔티티를 persist 하면 DB에는 저장되지 않는다.")
    void persistWithoutTransaction() {
        Long id = 999L;

        entityManager.persist(apiTestEntity);
        apiTestEntity.setId(id);

        assertThat(anotherManager.find(ApiTestEntity.class, id)).isNull();
    }

    @Test
    @DisplayName("flush하면 SQL 저장소의 쿼리가 DB에 전송될 뿐, DB에 변경사항이 커밋되지는 않는것 같다.")
    void flush() {
        EntityTransaction transaction = entityManager.getTransaction();
        Long id;

        transaction.begin();
        entityManager.persist(apiTestEntity);
        id = apiTestEntity.getId();
        entityManager.flush();

        assertThat(anotherManager.find(ApiTestEntity.class, id)).isNull();
        transaction.rollback();
    }

    @Test
    @DisplayName("트렌젝션이 없을 때 flush하면 TransactionRequiredException이 발생한다.")
    void flushWithoutTransaction() {
        entityManager.persist(apiTestEntity);
        assertThatThrownBy(() -> entityManager.flush())
                .isInstanceOf(TransactionRequiredException.class);
    }

    @AfterEach
    void tearDown() {
        entityManager.close();
    }

    @AfterAll
    static void tearDownAfterAll() {
        emf.close();
    }
}
