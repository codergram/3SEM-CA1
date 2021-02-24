package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.jokes.JokeDTO;
import facades.jokes.JokeFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 */
@Path("jokes")
public class JokeResource {

  private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
  private static final JokeFacade FACADE =  JokeFacade.getJokeFacade(EMF);
  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

  @Path("all")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public String getAll() {
    List<JokeDTO> jokes = FACADE.getAllJokes();
    return GSON.toJson(jokes);
  }

  @Path("{id}")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public String getById(@PathParam("id") int id){
    return GSON.toJson(FACADE.getById(id));
  }

  @Path("random")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public String random() {
    return GSON.toJson(FACADE.getRandomJoke());
  }
}
