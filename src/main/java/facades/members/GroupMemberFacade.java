package facades.members;

import dtos.members.GroupMemberDTO;
import entities.members.GroupMember;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 */
public class GroupMemberFacade {

    private static GroupMemberFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private GroupMemberFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static GroupMemberFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GroupMemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public GroupMemberDTO create(GroupMemberDTO memberDTO){
    GroupMember member =
        new GroupMember(memberDTO.getName(), memberDTO.getEmail(), memberDTO.getFavseries());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(member);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new GroupMemberDTO(member);
    }

    public GroupMemberDTO getByName(String name){
        EntityManager em = emf.createEntityManager();
        TypedQuery<GroupMember> query = em.createQuery("SELECT Members FROM GroupMember Members WHERE Members.name LIKE CONCAT('%',UPPER(:name),'%')", GroupMember.class).setParameter("name", name);
        return new GroupMemberDTO(query.getSingleResult());
    }

    public GroupMemberDTO getById(int id){
        EntityManager em = emf.createEntityManager();
        TypedQuery<GroupMember> query = em.createQuery("SELECT Members FROM GroupMember Members WHERE Members.id = :id", GroupMember.class).setParameter("id", id);
        return new GroupMemberDTO(query.getSingleResult());
    }
    
    public List<GroupMemberDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<GroupMember> query = em.createQuery("SELECT Members FROM GroupMember Members", GroupMember.class);
        List<GroupMember> rms = query.getResultList();
        return GroupMemberDTO.getDtos(rms);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        GroupMemberFacade fe = getFacade(emf);
        fe.getAll().forEach(System.out::println);
    }

}
