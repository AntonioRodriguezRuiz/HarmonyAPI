/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Episodes implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer episodeid;
    private Integer seasonid;
    private String  episodename;
    private Integer episodeno;

    public Episodes() {}

    public Episodes(Episodes value) {
        this.episodeid = value.episodeid;
        this.seasonid = value.seasonid;
        this.episodename = value.episodename;
        this.episodeno = value.episodeno;
    }

    public Episodes(
        Integer episodeid,
        Integer seasonid,
        String  episodename,
        Integer episodeno
    ) {
        this.episodeid = episodeid;
        this.seasonid = seasonid;
        this.episodename = episodename;
        this.episodeno = episodeno;
    }

    /**
     * Getter for <code>harmony.episodes.episodeid</code>.
     */
    public Integer getEpisodeid() {
        return this.episodeid;
    }

    /**
     * Setter for <code>harmony.episodes.episodeid</code>.
     */
    public Episodes setEpisodeid(Integer episodeid) {
        this.episodeid = episodeid;
        return this;
    }

    /**
     * Getter for <code>harmony.episodes.seasonid</code>.
     */
    public Integer getSeasonid() {
        return this.seasonid;
    }

    /**
     * Setter for <code>harmony.episodes.seasonid</code>.
     */
    public Episodes setSeasonid(Integer seasonid) {
        this.seasonid = seasonid;
        return this;
    }

    /**
     * Getter for <code>harmony.episodes.episodeName</code>.
     */
    public String getEpisodename() {
        return this.episodename;
    }

    /**
     * Setter for <code>harmony.episodes.episodeName</code>.
     */
    public Episodes setEpisodename(String episodename) {
        this.episodename = episodename;
        return this;
    }

    /**
     * Getter for <code>harmony.episodes.episodeNo</code>.
     */
    public Integer getEpisodeno() {
        return this.episodeno;
    }

    /**
     * Setter for <code>harmony.episodes.episodeNo</code>.
     */
    public Episodes setEpisodeno(Integer episodeno) {
        this.episodeno = episodeno;
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
        final Episodes other = (Episodes) obj;
        if (episodeid == null) {
            if (other.episodeid != null)
                return false;
        }
        else if (!episodeid.equals(other.episodeid))
            return false;
        if (seasonid == null) {
            if (other.seasonid != null)
                return false;
        }
        else if (!seasonid.equals(other.seasonid))
            return false;
        if (episodename == null) {
            if (other.episodename != null)
                return false;
        }
        else if (!episodename.equals(other.episodename))
            return false;
        if (episodeno == null) {
            if (other.episodeno != null)
                return false;
        }
        else if (!episodeno.equals(other.episodeno))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.episodeid == null) ? 0 : this.episodeid.hashCode());
        result = prime * result + ((this.seasonid == null) ? 0 : this.seasonid.hashCode());
        result = prime * result + ((this.episodename == null) ? 0 : this.episodename.hashCode());
        result = prime * result + ((this.episodeno == null) ? 0 : this.episodeno.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Episodes (");

        sb.append(episodeid);
        sb.append(", ").append(seasonid);
        sb.append(", ").append(episodename);
        sb.append(", ").append(episodeno);

        sb.append(")");
        return sb.toString();
    }
}