/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades.cars;

import dtos.cars.CarDTO;
import dtos.members.GroupMemberDTO;
import entities.cars.Car;
import entities.members.GroupMember;
import facades.members.GroupMemberFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * @author Tweny
 */
public class CarFacade {
    
    private static CarFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CarFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CarFacade getCarFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CarDTO create(CarDTO carDTO){
    Car car = new Car(carDTO.getBrand(), carDTO.getModel(), carDTO.getYear(), carDTO.getPrice());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new CarDTO(car);
    }
    
    public List<CarDTO> getCarByBrand(String brand){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Car> query = em.createQuery("SELECT car FROM Car car WHERE car.brand = :brand", Car.class).setParameter("brand", brand);
        List<Car> cars = query.getResultList();
        return CarDTO.convertMovieListToDTO(cars);
    }
    
     public CarDTO getCarById(long id){
        EntityManager em = emf.createEntityManager();
        return new CarDTO(em.find(Car.class, id));
    }
    
    public List<CarDTO> getAllCarDTOs(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Car> query = em.createQuery("SELECT car FROM Car car", Car.class);
        List<Car> cars = query.getResultList();
        return CarDTO.convertMovieListToDTO(cars);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        CarFacade fe = getCarFacade(emf);
        fe.getAllCarDTOs().forEach(dto->System.out.println(dto));
    }
    
}
