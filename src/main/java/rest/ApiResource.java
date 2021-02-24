package rest;

import facades.cars.CarPopulator;
import facades.jokes.JokePopulator;
import facades.members.GroupPopulator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Gaarde Nielsen (cph-at89@cphbusiness.dk)
 */
@Path("")
public class ApiResource {
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
        switch (key){
            case "guderstor":
                return GroupPopulator.populate();
            case "hakunamatata":
                return CarPopulator.populate();
            case "dadjokes":
                return JokePopulator.populate();
            default:
                return false;
        }
    }
}
