package api.helpers.request;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PlatformRequestHelper {
    private Integer userid;
    private Integer platformid;
    private String name;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getPlatformid() {
        return platformid;
    }

    public void setPlatformid(Integer platformid) {
        this.platformid = platformid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlatformRequestHelper(Integer userid, Integer platformid, String name) {
        this.userid = userid;
        this.platformid = platformid;
        this.name = name;
    }

    public void validate() {
        if (userid == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Userid is required");
        } else if (platformid == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Platformid is required");
        }
    }
}
