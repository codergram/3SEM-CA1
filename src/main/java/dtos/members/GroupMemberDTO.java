/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos.members;

import entities.members.GroupMember;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Emil
 */
public class GroupMemberDTO {
    private int id;
    private String name;
    private String email;
    private String[] favseries;

    public GroupMemberDTO(String name, String email, String[] favseries) {
        this.name = name;
        this.email = email;
        this.favseries = favseries;
    }

    public static List<GroupMemberDTO> getDtos(List<GroupMember> members){
        List<GroupMemberDTO> memberdtos = new ArrayList<>();
        members.forEach(member->memberdtos.add(new GroupMemberDTO(member)));
        return memberdtos;
    }


    public GroupMemberDTO(GroupMember member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.favseries = member.getFavseries();
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GroupMemberDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", favseries=").append(Arrays.toString(favseries));
        sb.append('}');
        return sb.toString();
    }
}
