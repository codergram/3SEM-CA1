package entities.members;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 */
@Entity
@NamedQuery(name = "GroupMember.deleteAllRows", query = "DELETE from GroupMember")
public class GroupMember implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public GroupMember() {
    }

    private String name;
    private String email;
    private String[] favseries;

    public GroupMember(String name, String email, String[] favseries) {
        this.name = name;
        this.email = email;
        this.favseries = favseries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getFavseries() {
        return favseries;
    }

    public void setFavseries(String[] favseries) {
        this.favseries = favseries;
    }
}
