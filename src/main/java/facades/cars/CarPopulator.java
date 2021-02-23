/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades.cars;

import dtos.cars.CarDTO;
import entities.cars.Car;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author Tweny
 */
public class CarPopulator {
    public static boolean populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        CarFacade fe = CarFacade.getCarFacade(emf);      
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(new Car("Ford", "E350", 1997, 3000));
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
        try{
        fe.create(new CarDTO(new Car("Ford", "E350", 1997, 3000)));
        fe.create(new CarDTO(new Car("Chevy", "Venture", 1999, 4900)));
        fe.create(new CarDTO(new Car("Chevy", "Venture", 2000, 5000)));
        fe.create(new CarDTO(new Car("Jeep", "Grand Cherokee", 1996, 4799)));
        fe.create(new CarDTO(new Car("Volvo", "V70", 2005, 44799)));
        return true;
        } catch (Exception e){
            return false;
        }
    }
    public static void main(String[] args) {
        populate();
    }
    
}
