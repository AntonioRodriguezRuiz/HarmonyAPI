package api.helpers.response;

import org.jooq.Record;

import static src.main.java.model.Tables.*;

public class EpisodeResponseHelper {
    private Integer mediaid;
    private Integer seasonid;
    private Integer episodeid;
    private Integer episodeNo;
    private String episodeName;

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

    public Integer getEpisodeid() {
        return episodeid;
    }

    public void setEpisodeid(Integer episodeid) {
        this.episodeid = episodeid;
    }

    public Integer getEpisodeNo() {
        return episodeNo;
    }

    public void setEpisodeNo(Integer episodeNo) {
        this.episodeNo = episodeNo;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public EpisodeResponseHelper(Record record) {
        this.mediaid = record.getValue(MEDIA.MEDIAID);
        this.seasonid = record.getValue(SEASONS.SEASONID);
        this.episodeid = record.getValue(EPISODES.EPISODEID);
        this.episodeNo = record.getValue(EPISODES.EPISODENO);
        this.episodeName = record.getValue(EPISODES.EPISODENAME);
    }
}
