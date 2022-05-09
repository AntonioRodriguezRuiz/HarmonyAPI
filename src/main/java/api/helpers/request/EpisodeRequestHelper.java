package api.helpers.request;

import org.jooq.Record;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
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

    public void validate() {
        if (userid == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Userid is required");
        } else if (episodeid == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "EpisodeId is required");
        }
    }

    public void postValidate() {
        if (userid == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Userid is required");
        } else if (episodeNo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "EpisodeNo is required");
        } else if (episodeName == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "EpisodeName is required");
        }
    }

}
