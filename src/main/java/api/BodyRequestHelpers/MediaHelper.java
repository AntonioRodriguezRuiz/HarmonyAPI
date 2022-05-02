package api.BodyRequestHelpers;

import java.time.LocalDate;

public class MediaHelper {
    private Integer userid;
    private Integer mediaid;
    private String title;
    private LocalDate releasedate;
    private String coverimage;
    private String backgroundimage;
    private String synopsis;

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

    public MediaHelper(Integer userid, Integer mediaid, String title, String releasedate, String coverimage, String backgroundimage, String synopsis) {
        this.userid=userid;
        this.mediaid=mediaid;
        this.title=title;
        this.releasedate=LocalDate.parse(releasedate);
        this.coverimage=coverimage;
        this.backgroundimage=backgroundimage;
        this.synopsis=synopsis;

    }

    public MediaHelper(){
        this.userid=null;
        this.mediaid=null;
        this.title=null;
        this.releasedate=null;
        this.coverimage=null;
        this.backgroundimage=null;
        this.synopsis=null;
    }
}
