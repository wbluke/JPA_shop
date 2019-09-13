package study.api.test.ch3;

import org.junit.jupiter.api.*;
import study.api.test.model.ApiTestEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;

class PersistMethodTest {
    private static final String TEST_NAME = "test entity";
    private static final Integer TEST_AGE = 28;
    private static EntityManagerFactory emf;

    private EntityManager entityManager;
    private ApiTestEntity apiTestEntity;

    @BeforeAll
    static void setupBeforeAll() {
        emf = Persistence.createEntityManagerFactory("jpabook");
    }

    @BeforeEach
    void setUp() {
        entityManager = emf.createEntityManager();
        apiTestEntity = new ApiTestEntity(TEST_NAME, TEST_AGE);
    }

    @Test
    @DisplayName("엔티티를 영속화하면 영속성 컨텍스트(엔티티 매니저)에 엔티티가 저장된다")
    void persist() {
        entityManager.persist(apiTestEntity);

        assertThat(entityManager.contains(apiTestEntity)).isTrue();
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
        EntityManager anotherManager = emf.createEntityManager();
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
        EntityManager anotherManager = emf.createEntityManager();
        Long id = 999L;

        entityManager.persist(apiTestEntity);
        apiTestEntity.setId(id);

        assertThat(anotherManager.find(ApiTestEntity.class, id)).isNull();
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
