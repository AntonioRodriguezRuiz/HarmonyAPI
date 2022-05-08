package api.helpers.request;

import api.helpers.enums.RoleType;
import org.jooq.Table;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PeopleMediaRequestHelper {
    private Integer userid;
    private Integer personid;
    private String role;
    private RoleType roleType;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public PeopleMediaRequestHelper(Integer userid, Integer personid, String role, RoleType roleType) {
        this.userid = userid;
        this.personid = personid;
        this.role = role;
        this.roleType = roleType;
    }

    public Byte getRoleTypeByte() {
        switch (this.roleType){
            case CREW: return Byte.valueOf("0");
            case CAST: return Byte.valueOf("1");
        }
        return null;
    }

    public void mediaValidate(Table table) {
        if(this.personid==null || this.role==null || this.roleType==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "personId, role and roleType cannot be null");
        else if(table.getName().equals("series"))
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "You cannot add a person to a series, only to its episodes");
        else if (table.getName().equals("books") && this.getRoleTypeByte() != 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Books do not have cast, only crew");
    }
}
