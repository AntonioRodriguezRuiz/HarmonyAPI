package api.helpers.response;

import org.jooq.Record;

import static src.main.java.model.Tables.SEASONS;

public class SeasonResponseHelper {
    private Integer mediaid;
    private Integer seasonid;

    public Integer getMediaid() {
        return mediaid;
    }

    public void setMediaid(Integer mediaid) {
        this.mediaid = mediaid;
    }

    public Integer getSeasonid() {
        return seasonid;
    }

    public void setSeasonid(Integer seasonid) {
        this.seasonid = seasonid;
    }

    private Integer seasonNo;
    private Integer noEpisodes;

    public Integer getSeasonNo() {
        return seasonNo;
    }

    public void setSeasonNo(Integer seasonNo) {
        this.seasonNo = seasonNo;
    }

    public Integer getNoEpisodes() {
        return noEpisodes;
    }

    public void setNoEpisodes(Integer noEpisodes) {
        this.noEpisodes = noEpisodes;
    }

    public SeasonResponseHelper(Record record) {
        this.mediaid = record.getValue(SEASONS.SERIESID);
        this.seasonid = record.getValue(SEASONS.SEASONID);
        this.seasonNo = record.getValue(SEASONS.SEASONNO);
        this.noEpisodes = record.getValue(SEASONS.NOEPISODES);
    }

}
