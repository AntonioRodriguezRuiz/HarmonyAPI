/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Lists implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer       listid;
    private Integer       userid;
    private String        listname;
    private String        icon;
    private LocalDateTime creationdate;
    private LocalDateTime modificationdate;

    public Lists() {}

    public Lists(Lists value) {
        this.listid = value.listid;
        this.userid = value.userid;
        this.listname = value.listname;
        this.icon = value.icon;
        this.creationdate = value.creationdate;
        this.modificationdate = value.modificationdate;
    }

    public Lists(
        Integer       listid,
        Integer       userid,
        String        listname,
        String        icon,
        LocalDateTime creationdate,
        LocalDateTime modificationdate
    ) {
        this.listid = listid;
        this.userid = userid;
        this.listname = listname;
        this.icon = icon;
        this.creationdate = creationdate;
        this.modificationdate = modificationdate;
    }

    /**
     * Getter for <code>harmony.lists.listid</code>.
     */
    public Integer getListid() {
        return this.listid;
    }

    /**
     * Setter for <code>harmony.lists.listid</code>.
     */
    public Lists setListid(Integer listid) {
        this.listid = listid;
        return this;
    }

    /**
     * Getter for <code>harmony.lists.userid</code>.
     */
    public Integer getUserid() {
        return this.userid;
    }

    /**
     * Setter for <code>harmony.lists.userid</code>.
     */
    public Lists setUserid(Integer userid) {
        this.userid = userid;
        return this;
    }

    /**
     * Getter for <code>harmony.lists.listName</code>.
     */
    public String getListname() {
        return this.listname;
    }

    /**
     * Setter for <code>harmony.lists.listName</code>.
     */
    public Lists setListname(String listname) {
        this.listname = listname;
        return this;
    }

    /**
     * Getter for <code>harmony.lists.icon</code>.
     */
    public String getIcon() {
        return this.icon;
    }

    /**
     * Setter for <code>harmony.lists.icon</code>.
     */
    public Lists setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    /**
     * Getter for <code>harmony.lists.creationDate</code>.
     */
    public LocalDateTime getCreationdate() {
        return this.creationdate;
    }

    /**
     * Setter for <code>harmony.lists.creationDate</code>.
     */
    public Lists setCreationdate(LocalDateTime creationdate) {
        this.creationdate = creationdate;
        return this;
    }

    /**
     * Getter for <code>harmony.lists.modificationDate</code>.
     */
    public LocalDateTime getModificationdate() {
        return this.modificationdate;
    }

    /**
     * Setter for <code>harmony.lists.modificationDate</code>.
     */
    public Lists setModificationdate(LocalDateTime modificationdate) {
        this.modificationdate = modificationdate;
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
        final Lists other = (Lists) obj;
        if (listid == null) {
            if (other.listid != null)
                return false;
        }
        else if (!listid.equals(other.listid))
            return false;
        if (userid == null) {
            if (other.userid != null)
                return false;
        }
        else if (!userid.equals(other.userid))
            return false;
        if (listname == null) {
            if (other.listname != null)
                return false;
        }
        else if (!listname.equals(other.listname))
            return false;
        if (icon == null) {
            if (other.icon != null)
                return false;
        }
        else if (!icon.equals(other.icon))
            return false;
        if (creationdate == null) {
            if (other.creationdate != null)
                return false;
        }
        else if (!creationdate.equals(other.creationdate))
            return false;
        if (modificationdate == null) {
            if (other.modificationdate != null)
                return false;
        }
        else if (!modificationdate.equals(other.modificationdate))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.listid == null) ? 0 : this.listid.hashCode());
        result = prime * result + ((this.userid == null) ? 0 : this.userid.hashCode());
        result = prime * result + ((this.listname == null) ? 0 : this.listname.hashCode());
        result = prime * result + ((this.icon == null) ? 0 : this.icon.hashCode());
        result = prime * result + ((this.creationdate == null) ? 0 : this.creationdate.hashCode());
        result = prime * result + ((this.modificationdate == null) ? 0 : this.modificationdate.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Lists (");

        sb.append(listid);
        sb.append(", ").append(userid);
        sb.append(", ").append(listname);
        sb.append(", ").append(icon);
        sb.append(", ").append(creationdate);
        sb.append(", ").append(modificationdate);

        sb.append(")");
        return sb.toString();
    }
}
