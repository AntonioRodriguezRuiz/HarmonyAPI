package api.helpers.request;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SeasonRequestHelper {
    private Integer userid;
    private Integer seasonid;

    private Integer seasonNo;
    private Integer noEpisodes;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSeasonid() {
        return seasonid;
    }

    public void setSeasonid(Integer seasonid) {
        this.seasonid = seasonid;
    }

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

    public SeasonRequestHelper(Integer userid, Integer mediaid, Integer seasonid, Integer seasonNo, Integer noEpisodes) {
        this.userid = userid;
        this.seasonid = seasonid;
        this.seasonNo = seasonNo;
        this.noEpisodes = noEpisodes;
    }

    public void validate() {
        if (userid == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Userid is required");
        } else if (seasonid == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seasonid is required");
        }
    }

    public void postValidate() {
        if (userid == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Userid is required");
        } else if(seasonNo==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "SeasonNo is required");
        } else if(noEpisodes==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "noEpisodes is required");
        }
    }
}
