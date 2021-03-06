/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Platforms implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer platformid;
    private String  platformname;

    public Platforms() {}

    public Platforms(Platforms value) {
        this.platformid = value.platformid;
        this.platformname = value.platformname;
    }

    public Platforms(
        Integer platformid,
        String  platformname
    ) {
        this.platformid = platformid;
        this.platformname = platformname;
    }

    /**
     * Getter for <code>harmony.platforms.platformid</code>.
     */
    public Integer getPlatformid() {
        return this.platformid;
    }

    /**
     * Setter for <code>harmony.platforms.platformid</code>.
     */
    public Platforms setPlatformid(Integer platformid) {
        this.platformid = platformid;
        return this;
    }

    /**
     * Getter for <code>harmony.platforms.platformName</code>.
     */
    public String getPlatformname() {
        return this.platformname;
    }

    /**
     * Setter for <code>harmony.platforms.platformName</code>.
     */
    public Platforms setPlatformname(String platformname) {
        this.platformname = platformname;
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
        final Platforms other = (Platforms) obj;
        if (platformid == null) {
            if (other.platformid != null)
                return false;
        }
        else if (!platformid.equals(other.platformid))
            return false;
        if (platformname == null) {
            if (other.platformname != null)
                return false;
        }
        else if (!platformname.equals(other.platformname))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.platformid == null) ? 0 : this.platformid.hashCode());
        result = prime * result + ((this.platformname == null) ? 0 : this.platformname.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Platforms (");

        sb.append(platformid);
        sb.append(", ").append(platformname);

        sb.append(")");
        return sb.toString();
    }
}
