package facades.members;

import dtos.members.GroupMemberDTO;
import entities.members.GroupMember;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 */
public class GroupPopulator {
    public static boolean populate(){
        System.out.println("Running " + GroupPopulator.class.getSimpleName());
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        GroupMemberFacade fe = GroupMemberFacade.getFacade(emf);
        try{
        fe.create(new GroupMemberDTO(new GroupMember("Emil Elkjær Nielsen", "cph-en93@cphbusiness.dk", new String[]{"Keeping up with the kardasians", "Matador"})));
        fe.create(new GroupMemberDTO(new GroupMember("Sigurd Arik Gaarde Nielsen", "cph-at89@cphbusiness.dk", new String[]{"Huset på Christianshavn", "SWAT"})));
        fe.create(new GroupMemberDTO(new GroupMember("Jacob Lange Nielsen", "cph-jn352@cphbusiness.dk", new String[]{"Ex on the Beach - Norge", "Paradise Hotel"})));
        return true;
        } catch (Exception e){
            return false;
        }
    }
    public static void main(String[] args) {
        populate();
    }
}
