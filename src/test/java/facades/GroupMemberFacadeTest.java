package facades;

import entities.members.GroupMember;
import facades.members.GroupMemberFacade;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GroupMemberFacadeTest {

    private static EntityManagerFactory emf;
    private static GroupMemberFacade facade;

    public GroupMemberFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = GroupMemberFacade.getFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("GroupMember.deleteAllRows").executeUpdate();
            em.persist(
                    new GroupMember(
                        "Emil",
                        "cph-en93@cphbusiness.dk",
                        new String[]{"Keeping up with the kardasians", "Matador"})
                );

            em.persist(
                    new GroupMember(
                        "Arik",
                        "cph-at89@cphbusiness.dk",
                        new String[]{"Huset på Christianshavn", "SWAT"})
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
        assertEquals(2, facade.getAll().size(), "Expects two rows in the database");
    }
    

}
