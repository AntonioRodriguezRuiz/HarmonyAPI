/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Peoplebooks implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer peoplebooksid;
    private Integer bookid;
    private Integer personid;
    private String  role;
    private Byte    roletype;

    public Peoplebooks() {}

    public Peoplebooks(Peoplebooks value) {
        this.peoplebooksid = value.peoplebooksid;
        this.bookid = value.bookid;
        this.personid = value.personid;
        this.role = value.role;
        this.roletype = value.roletype;
    }

    public Peoplebooks(
        Integer peoplebooksid,
        Integer bookid,
        Integer personid,
        String  role,
        Byte    roletype
    ) {
        this.peoplebooksid = peoplebooksid;
        this.bookid = bookid;
        this.personid = personid;
        this.role = role;
        this.roletype = roletype;
    }

    /**
     * Getter for <code>harmony.peopleBooks.peopleBooksid</code>.
     */
    public Integer getPeoplebooksid() {
        return this.peoplebooksid;
    }

    /**
     * Setter for <code>harmony.peopleBooks.peopleBooksid</code>.
     */
    public Peoplebooks setPeoplebooksid(Integer peoplebooksid) {
        this.peoplebooksid = peoplebooksid;
        return this;
    }

    /**
     * Getter for <code>harmony.peopleBooks.bookid</code>.
     */
    public Integer getBookid() {
        return this.bookid;
    }

    /**
     * Setter for <code>harmony.peopleBooks.bookid</code>.
     */
    public Peoplebooks setBookid(Integer bookid) {
        this.bookid = bookid;
        return this;
    }

    /**
     * Getter for <code>harmony.peopleBooks.personid</code>.
     */
    public Integer getPersonid() {
        return this.personid;
    }

    /**
     * Setter for <code>harmony.peopleBooks.personid</code>.
     */
    public Peoplebooks setPersonid(Integer personid) {
        this.personid = personid;
        return this;
    }

    /**
     * Getter for <code>harmony.peopleBooks.role</code>.
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Setter for <code>harmony.peopleBooks.role</code>.
     */
    public Peoplebooks setRole(String role) {
        this.role = role;
        return this;
    }

    /**
     * Getter for <code>harmony.peopleBooks.roletype</code>.
     */
    public Byte getRoletype() {
        return this.roletype;
    }

    /**
     * Setter for <code>harmony.peopleBooks.roletype</code>.
     */
    public Peoplebooks setRoletype(Byte roletype) {
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
        final Peoplebooks other = (Peoplebooks) obj;
        if (peoplebooksid == null) {
            if (other.peoplebooksid != null)
                return false;
        }
        else if (!peoplebooksid.equals(other.peoplebooksid))
            return false;
        if (bookid == null) {
            if (other.bookid != null)
                return false;
        }
        else if (!bookid.equals(other.bookid))
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
        result = prime * result + ((this.peoplebooksid == null) ? 0 : this.peoplebooksid.hashCode());
        result = prime * result + ((this.bookid == null) ? 0 : this.bookid.hashCode());
        result = prime * result + ((this.personid == null) ? 0 : this.personid.hashCode());
        result = prime * result + ((this.role == null) ? 0 : this.role.hashCode());
        result = prime * result + ((this.roletype == null) ? 0 : this.roletype.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Peoplebooks (");

        sb.append(peoplebooksid);
        sb.append(", ").append(bookid);
        sb.append(", ").append(personid);
        sb.append(", ").append(role);
        sb.append(", ").append(roletype);

        sb.append(")");
        return sb.toString();
    }
}
