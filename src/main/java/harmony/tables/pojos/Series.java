/*
 * This file is generated by jOOQ.
 */
package src.main.java.harmony.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Series implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer seriesid;
    private Integer mediaid;

    public Series() {}

    public Series(Series value) {
        this.seriesid = value.seriesid;
        this.mediaid = value.mediaid;
    }

    public Series(
        Integer seriesid,
        Integer mediaid
    ) {
        this.seriesid = seriesid;
        this.mediaid = mediaid;
    }

    /**
     * Getter for <code>harmony.series.seriesid</code>.
     */
    public Integer getSeriesid() {
        return this.seriesid;
    }

    /**
     * Setter for <code>harmony.series.seriesid</code>.
     */
    public Series setSeriesid(Integer seriesid) {
        this.seriesid = seriesid;
        return this;
    }

    /**
     * Getter for <code>harmony.series.mediaid</code>.
     */
    public Integer getMediaid() {
        return this.mediaid;
    }

    /**
     * Setter for <code>harmony.series.mediaid</code>.
     */
    public Series setMediaid(Integer mediaid) {
        this.mediaid = mediaid;
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
        final Series other = (Series) obj;
        if (seriesid == null) {
            if (other.seriesid != null)
                return false;
        }
        else if (!seriesid.equals(other.seriesid))
            return false;
        if (mediaid == null) {
            if (other.mediaid != null)
                return false;
        }
        else if (!mediaid.equals(other.mediaid))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.seriesid == null) ? 0 : this.seriesid.hashCode());
        result = prime * result + ((this.mediaid == null) ? 0 : this.mediaid.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Series (");

        sb.append(seriesid);
        sb.append(", ").append(mediaid);

        sb.append(")");
        return sb.toString();
    }
}
