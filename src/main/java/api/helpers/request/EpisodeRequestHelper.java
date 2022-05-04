package api.helpers.request;

import org.jooq.Record;

public class EpisodeRequestHelper {
    private Integer userid;
    private Integer episodeid;
    private Integer episodeNo;
    private String episodeName;


    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public EpisodeRequestHelper(Integer userid, Integer episodeid, Integer episodeNo, String episodeName) {
        this.userid = userid;
        this.episodeid = episodeid;
        this.episodeNo = episodeNo;
        this.episodeName = episodeName;
    }

}
