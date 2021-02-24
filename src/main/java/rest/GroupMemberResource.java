package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.members.GroupMemberDTO;
import facades.members.GroupMemberFacade;
import java.util.ArrayList;
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
       
    private static final GroupMemberFacade FACADE =  GroupMemberFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

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
            case "jacob":
                return jacobWork();
            default:
                return allWork();
        }
    }

    private String emilWork(){
        List<String> worklist = new ArrayList<>();
        worklist.add("Droplet setup");
        worklist.add("Github repository");
        worklist.add("Group members (REST, JPA, JS, HTML)");
        worklist.add("Group page (REST, JS, HTML)");
        worklist.add("Jokes page (REST, JPA, JS, HTML)");

        return GSON.toJson(
            new WorkMade("Emil", worklist)
        );
    }

    private String arikWork(){
        List<String> worklist = new ArrayList<>();
        worklist.add("Car Entity and JPA");
        worklist.add("Car REST/API");
        worklist.add("Car test UNI/REST");
        worklist.add("Car for sale JavaScript html");

        return GSON.toJson(
            new WorkMade("Arik", worklist)
        );
    }

    // TODO: Write content @Jacob
    // @body Få skrevet ind hvilke ting du har lavet.
    // Husk at fjerne disse to kommentarer inden du pusher.
    private String jacobWork(){
        List<String> worklist = new ArrayList<>();
        worklist.add("xxxxxx");

        return GSON.toJson(
            new WorkMade("Jacob", worklist)
        );
    }

    private String allWork(){
        List<String> worklist = new ArrayList<>();
        worklist.add("Group contract");

        return GSON.toJson(
            new WorkMade("All", worklist)
        );
    }
}

class WorkMade{
    String groupMember;
    List<String> workdone;

    public WorkMade(String groupMember, List<String> workdone) {
        this.groupMember = groupMember;
        this.workdone = workdone;
    }

    public void addToList(String workdone) {
        this.workdone.add(workdone);
    }

    public String getGroupMember() {
        return groupMember;
    }

    public List<String> getWorkdone() {
        return workdone;
    }
}
