package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.cars.CarPopulator;
import facades.members.GroupPopulator;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * @author Emil
 */
@Path("")
public class ApiResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Path("isalive")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String alive() {
        return "{\"msg\":\"I am alive\"}";
    }

    @Path("populatedata/{key}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public boolean populate(@PathParam("key") String key) {
        if (key.equalsIgnoreCase("guderstor")) {
            return GroupPopulator.populate();
        } else if (key.equalsIgnoreCase("hakuna-matata")){
            return CarPopulator.populate();
        }
        return false;
    }
}
