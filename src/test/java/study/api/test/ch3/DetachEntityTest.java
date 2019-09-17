package study.api.test.ch3;

import org.junit.jupiter.api.*;
import study.api.test.model.ApiTestEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static study.api.test.ch3.ManagedEntityTest.TEST_AGE;
import static study.api.test.ch3.ManagedEntityTest.TEST_NAME;

public class DetachEntityTest {
    private static EntityManagerFactory emf;

    @BeforeAll
    static void setUpBeforeAll() {
        emf = Persistence.createEntityManagerFactory("jpabook");
    }

    private EntityManager entityManager;
    private EntityManager anotherManager;
    private ApiTestEntity apiTestEntity;

    @BeforeEach
    void setUp() {
        entityManager = emf.createEntityManager();
        anotherManager = emf.createEntityManager();
        apiTestEntity = new ApiTestEntity(TEST_NAME, TEST_AGE);
    }

    @Test
    @DisplayName("영속 엔티티를 detach하면 영속성 컨테스트에서 분리된다.")
    void detach() {
        entityManager.persist(apiTestEntity);
        entityManager.detach(apiTestEntity);

        assertThat(entityManager.contains(apiTestEntity)).isFalse();
    }

    @Test
    @DisplayName("엔티티를 DB에 저장한 이후에 영속성 컨텍스트에서 detach하더라도 DB에서 제거되지는 않는다.")
    void detachAfterCommit() {
        EntityTransaction transaction = entityManager.getTransaction();
        Long id;

        transaction.begin();
        entityManager.persist(apiTestEntity);
        id = apiTestEntity.getId();
        transaction.commit();

        assertThat(anotherManager.find(ApiTestEntity.class, id)).isEqualTo(apiTestEntity);
    }

    @Test
    @DisplayName("영속성 컨텍스트를 초기화하면 안에 있던 모든 엔티티는 준영속상태가 된다.")
    void clear() {
        // Given
        ApiTestEntity apiTestEntity2 = new ApiTestEntity(TEST_NAME, TEST_AGE);

        entityManager.persist(apiTestEntity);
        entityManager.persist(apiTestEntity2);

        // When
        entityManager.clear();

        // Then
        assertThat(entityManager.contains(apiTestEntity)).isFalse();
        assertThat(entityManager.contains(apiTestEntity2)).isFalse();
    }

    @Test
    @DisplayName("영속성 컨텍스트를 종료하더라도 영속성 컨텍스트가 바로 사라지지는 않는다.")
    void entityManagerAfterClose() {
        entityManager.close();

        assertThat(entityManager).isNotNull();
    }

    @Test
    @DisplayName("준영속 상태의 엔티티를 병합하면 영속 상태의 엔티티가 새로 만들어진다.")
    void merge() {
        // Given
        String updatedName = "updated name";
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        ApiTestEntity apiTestEntity = saveApiTestEntity(TEST_NAME, TEST_AGE);
        ApiTestEntity mergedEntity;

        // When
        apiTestEntity.setName(updatedName);

        transaction.begin();
        mergedEntity = entityManager.merge(apiTestEntity);
        transaction.commit();

        // Then
        assertThat(apiTestEntity.getName()).isEqualTo(updatedName);
        assertThat(mergedEntity.getName()).isEqualTo(updatedName);
        assertThat(entityManager.contains(apiTestEntity)).isFalse();
        assertThat(entityManager.contains(mergedEntity)).isTrue();
    }

    private ApiTestEntity saveApiTestEntity(String testName, Integer testAge) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        ApiTestEntity apiTestEntity = new ApiTestEntity(testName, testAge);
        entityManager.persist(apiTestEntity);

        transaction.commit();

        entityManager.close();
        return apiTestEntity;
    }

    @AfterEach
    void tearDown() {
        entityManager.close();
        anotherManager.close();
    }

    @AfterAll
    static void tearDownAfterAll() {
        emf.close();
    }
}
