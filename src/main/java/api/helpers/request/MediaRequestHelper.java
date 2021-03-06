package api.helpers.request;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

public class MediaRequestHelper {
    private Integer userid;
    private Integer mediaid;
    private String title;
    private LocalDate releasedate;
    private String coverimage;
    private String backgroundimage;
    private String synopsis;
    private Integer externalId;

    public Integer getUserid() {
        return userid;
    }

    public Integer getMediaid() {
        return mediaid;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleasedate() {
        return releasedate;
    }

    public String getCoverimage() {
        return coverimage;
    }

    public String getBackgroundimage() {
        return backgroundimage;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public Integer getExternalId() {
        return externalId;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setMediaid(Integer mediaid) {
        this.mediaid = mediaid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleasedate(LocalDate releasedate) {
        this.releasedate = releasedate;
    }

    public void setCoverimage(String coverimage) {
        this.coverimage = coverimage;
    }

    public void setBackgroundimage(String backgroundimage) {
        this.backgroundimage = backgroundimage;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    public MediaRequestHelper(Integer userid, Integer mediaid, String title, String releasedate, String coverimage, String backgroundimage, String synopsis, Integer externalId) {
        this.userid=userid;
        this.mediaid=mediaid;
        this.title=title;
        this.releasedate=(releasedate==null ? null: LocalDate.parse(releasedate));
        this.coverimage=coverimage;
        this.backgroundimage=backgroundimage;
        this.synopsis=synopsis;
        this.externalId=externalId;
    }

    public void validate() {
        if (this.getTitle() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title is required");
        } else if (this.getReleasedate() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Release date is required");
        } else if (this.getSynopsis() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Synopsis is required");
        }
    }
}
