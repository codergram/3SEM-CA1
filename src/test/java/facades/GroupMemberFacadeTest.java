/**
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 */
package facades;

import dtos.members.GroupMemberDTO;
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

    GroupMember r1;

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
            r1 = new GroupMember(
                "Emil",
                "cph-en93@cphbusiness.dk",
                new String[]{"Keeping up with the kardasians", "Matador"});

            em.getTransaction().begin();
            em.createNamedQuery("GroupMember.deleteAllRows").executeUpdate();
            em.persist(
                    r1
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

    @Test
    void getById(){
        int expected = 1;
        assertEquals(expected, facade.getById(expected).getId(), "Expects " + expected + " to be in the database");
    }

    @Test
    void getByName(){
        String expected = r1.getName();
        assertEquals(expected, facade.getByName("emil").getName(), "Expects " + expected + " to be in the database");
    }

    @Test
    void createMember(){
        facade.create(
            new GroupMemberDTO(
                new GroupMember("Testmand", "cph-test12@cphbusiness.dk", new String[]{})
            ));
        assertEquals(3, facade.getAll().size(), "Expects three rows in the database");
    }
    

}
