/**
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 */
package facades;

import static org.junit.jupiter.api.Assertions.assertEquals;

import entities.jokes.Joke;
import facades.jokes.JokeFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

class JokeFacadeTest {

    private static EntityManagerFactory emf;
    private static JokeFacade facade;

    public JokeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = JokeFacade.getJokeFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.persist(
                    new Joke()
                );

            em.persist(
                    new Joke()
                );

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    void countInDatabase() {
        assertEquals(2, facade.getAllJokes().size(), "Expects two rows in the database");
    }
    

}
