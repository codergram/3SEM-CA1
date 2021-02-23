/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.cars.CarDTO;
import dtos.members.GroupMemberDTO;
import utils.EMF_Creator;
import facades.FacadeExample;
import facades.cars.CarFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Tweny
 */
//Todo Remove or change relevant parts before ACTUAL use
@Path("cars")
public class CarResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final CarFacade FACADE =  CarFacade.getCarFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @Path("isalive")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"I am alive\"}";
    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllCars() {
        List<CarDTO> carDTOs = FACADE.getAllCarDTOs();
        return GSON.toJson(carDTOs);
    }
    
    @Path("id/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarById(@PathParam("id") long id) {
        CarDTO carDTO = FACADE.getCarById(id);
        return GSON.toJson(carDTO);
    }
    
    @Path("brand/{brand}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarByBrand(@PathParam("brand") String brand) {
        List<CarDTO> carDTOs = FACADE.getCarByBrand(brand);
        return GSON.toJson(carDTOs);
    }
}