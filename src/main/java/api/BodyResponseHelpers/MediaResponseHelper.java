package api.BodyResponseHelpers;

import java.time.LocalDate;
import java.util.List;

import org.jooq.impl.QOM;
import src.main.java.HarmonyDatabase.tables.pojos.Genres;

public class MediaResponseHelper {
    private Integer mediaid;
    private String title;
    private LocalDate releasedate;
    private String coverimage;
    private String backgroundimage;
    private String synopsis;
    private List<Genres> genresList;

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

    public List<Genres> getGenresList(){
        return genresList;
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

    public void setGenresList(List<Genres> genresList){
        this.genresList=genresList;
    }

    public MediaResponseHelper(Integer mediaid, String title, LocalDate releasedate, String coverimage, String backgroundimage, String synopsis, List<Genres> genresList) {
        this.mediaid=mediaid;
        this.title=title;
        this.releasedate=releasedate;
        this.coverimage=coverimage;
        this.backgroundimage=backgroundimage;
        this.synopsis=synopsis;
        this.genresList = genresList;
    }
}
