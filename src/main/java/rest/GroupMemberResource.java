package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.members.GroupMemberDTO;
import facades.members.GroupMemberFacade;
import java.util.List;
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
@Path("groupmembers")
public class GroupMemberResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final GroupMemberFacade FACADE =  GroupMemberFacade.getFacadeExample(EMF);
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
    public String getAll() {
        List<GroupMemberDTO> members = FACADE.getAll();
        return GSON.toJson(members);
    }

    @Path("name/{name}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getById(@PathParam("name") String name) {
        GroupMemberDTO member = FACADE.getByName(name);
        return GSON.toJson(member);
    }



    @Path("madeby/{name}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String madeby(@PathParam("name") String name) {
        switch (name){
            case "emil":
                return emilWork();
            case "arik":
                return arikWork();
            case "jacbo":
                return jacobWork();
            case "all":
                return allWork();
            default:
                return allWork();
        }
    }

    private String createMsg(String msg){
        return String.format("{\"msg\":\"%s\"}", msg);
    }

    private String emilWork(){
        return createMsg("DevOps");
    }

    private String arikWork(){
        return createMsg("Javascript");
    }

    private String jacobWork(){
        return createMsg("Pancakes");
    }

    private String allWork(){
        return createMsg("A awesome REST API and JS front");
    }
}
