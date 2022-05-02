package api.BodyRequestHelpers;

public class MovieHelper{
    private Integer userid;
    private Integer mediaid;
    private Integer movieid;
    private String title;
    private String releasedate;
    private String coverimage;
    private String backgroundimage;
    private String synopsis;

    public Integer getUserid() {
        return userid;
    }

    public Integer getMediaid() {
        return mediaid;
    }

    public Integer getMovieid() {
        return movieid;
    }

    public String getTitle() {
        return title;
    }

    public String getReleasedate() {
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

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleasedate(String releasedate) {
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

    public MovieHelper(Integer userid, Integer mediaid, Integer movieid, String title, String releasedate, String coverimage, String backgroundimage,
                       String synopsis){
        this.userid = userid;
        this.mediaid = mediaid;
        this.movieid = movieid;
        this.title = title;
        this.releasedate = releasedate;
        this.coverimage = coverimage;
        this.backgroundimage = backgroundimage;
        this.synopsis = synopsis;
    }
}
