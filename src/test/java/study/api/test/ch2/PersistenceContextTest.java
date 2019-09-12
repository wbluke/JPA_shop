package study.api.test.ch2;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;

class PersistenceContextTest {
    private static EntityManagerFactory emf;

    @BeforeAll
    static void setUp() {
        emf = Persistence.createEntityManagerFactory("jpabook");
    }

    @Test
    @DisplayName("엔티티 매니저 펙토리가 생성된다")
    void createEntityManagerFactory() {
        assertThat(emf).isNotNull();
    }

    @Test
    @DisplayName("엔티티 매니저가 생성된다")
    void createEntityManager() {
        EntityManager entityManager = emf.createEntityManager();

        assertThat(entityManager).isNotNull();
    }

    @Test
    @DisplayName("트렌젝션이 생성된다")
    void createTransaction() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        assertThat(tx).isNotNull();
    }

    @AfterAll
    static void tearDown() {
        emf.close();
    }
}