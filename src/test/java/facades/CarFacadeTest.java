/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.RenameMe;
import entities.cars.Car;
import facades.cars.CarFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author Tweny
 */
//Uncomment the line below, to temporarily disable this test
//@Disabled
public class CarFacadeTest {

    private static EntityManagerFactory EMF;
    private static CarFacade FACADE;
    private static Car car1, car2;

    public CarFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        EMF = EMF_Creator.createEntityManagerFactoryForTest();
        FACADE = CarFacade.getCarFacade(EMF);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = EMF.createEntityManager();
        car1 = new Car("Brand 1", "Model 1", 1999, 30000);
        car2 = new Car("Brand 2", "Model 2", 2021, 100000);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            em.persist(car1);
            em.persist(car2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testDBSize() {
        assertEquals(2, FACADE.getAllCarDTOs().size(), "Expects two rows in the database");
    }
    
    @Test
    public void testObjectById(){
        assertEquals("Brand 1", FACADE.getCarById(car1.getId()).getBrand(), "Expected brand: 'Brand 1'");
    }

}
