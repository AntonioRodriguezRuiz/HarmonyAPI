/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Peopleepisodes implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer peopleepisodesid;
    private Integer episodeid;
    private Integer personid;
    private String  role;
    private Byte    roletype;

    public Peopleepisodes() {}

    public Peopleepisodes(Peopleepisodes value) {
        this.peopleepisodesid = value.peopleepisodesid;
        this.episodeid = value.episodeid;
        this.personid = value.personid;
        this.role = value.role;
        this.roletype = value.roletype;
    }

    public Peopleepisodes(
        Integer peopleepisodesid,
        Integer episodeid,
        Integer personid,
        String  role,
        Byte    roletype
    ) {
        this.peopleepisodesid = peopleepisodesid;
        this.episodeid = episodeid;
        this.personid = personid;
        this.role = role;
        this.roletype = roletype;
    }

    /**
     * Getter for <code>harmony.peopleEpisodes.peopleEpisodesid</code>.
     */
    public Integer getPeopleepisodesid() {
        return this.peopleepisodesid;
    }

    /**
     * Setter for <code>harmony.peopleEpisodes.peopleEpisodesid</code>.
     */
    public Peopleepisodes setPeopleepisodesid(Integer peopleepisodesid) {
        this.peopleepisodesid = peopleepisodesid;
        return this;
    }

    /**
     * Getter for <code>harmony.peopleEpisodes.episodeid</code>.
     */
    public Integer getEpisodeid() {
        return this.episodeid;
    }

    /**
     * Setter for <code>harmony.peopleEpisodes.episodeid</code>.
     */
    public Peopleepisodes setEpisodeid(Integer episodeid) {
        this.episodeid = episodeid;
        return this;
    }

    /**
     * Getter for <code>harmony.peopleEpisodes.personid</code>.
     */
    public Integer getPersonid() {
        return this.personid;
    }

    /**
     * Setter for <code>harmony.peopleEpisodes.personid</code>.
     */
    public Peopleepisodes setPersonid(Integer personid) {
        this.personid = personid;
        return this;
    }

    /**
     * Getter for <code>harmony.peopleEpisodes.role</code>.
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Setter for <code>harmony.peopleEpisodes.role</code>.
     */
    public Peopleepisodes setRole(String role) {
        this.role = role;
        return this;
    }

    /**
     * Getter for <code>harmony.peopleEpisodes.roletype</code>.
     */
    public Byte getRoletype() {
        return this.roletype;
    }

    /**
     * Setter for <code>harmony.peopleEpisodes.roletype</code>.
     */
    public Peopleepisodes setRoletype(Byte roletype) {
        this.roletype = roletype;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Peopleepisodes other = (Peopleepisodes) obj;
        if (peopleepisodesid == null) {
            if (other.peopleepisodesid != null)
                return false;
        }
        else if (!peopleepisodesid.equals(other.peopleepisodesid))
            return false;
        if (episodeid == null) {
            if (other.episodeid != null)
                return false;
        }
        else if (!episodeid.equals(other.episodeid))
            return false;
        if (personid == null) {
            if (other.personid != null)
                return false;
        }
        else if (!personid.equals(other.personid))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        }
        else if (!role.equals(other.role))
            return false;
        if (roletype == null) {
            if (other.roletype != null)
                return false;
        }
        else if (!roletype.equals(other.roletype))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.peopleepisodesid == null) ? 0 : this.peopleepisodesid.hashCode());
        result = prime * result + ((this.episodeid == null) ? 0 : this.episodeid.hashCode());
        result = prime * result + ((this.personid == null) ? 0 : this.personid.hashCode());
        result = prime * result + ((this.role == null) ? 0 : this.role.hashCode());
        result = prime * result + ((this.roletype == null) ? 0 : this.roletype.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Peopleepisodes (");

        sb.append(peopleepisodesid);
        sb.append(", ").append(episodeid);
        sb.append(", ").append(personid);
        sb.append(", ").append(role);
        sb.append(", ").append(roletype);

        sb.append(")");
        return sb.toString();
    }
}
