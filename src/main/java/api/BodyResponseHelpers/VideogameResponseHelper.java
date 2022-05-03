package api.BodyResponseHelpers;

import org.jooq.Record;
import src.main.java.HarmonyDatabase.tables.pojos.Genres;

import java.time.LocalDate;
import java.util.List;

import static src.main.java.HarmonyDatabase.Tables.VIDEOGAMES;
import static src.main.java.HarmonyDatabase.tables.Media.MEDIA;
import src.main.java.HarmonyDatabase.tables.pojos.Platforms;
public class VideogameResponseHelper extends MediaResponseHelper{

    private String company;
    private List<Platforms> platforms;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<Platforms> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platforms> platforms) {
        this.platforms = platforms;
    }

    public VideogameResponseHelper(Integer userid, Integer mediaid, String title, LocalDate releasedate, String coverimage, String backgroundimage, String synopsis, List<Genres> genresList, String company, List<Platforms> platforms) {
        super(mediaid, title, releasedate, coverimage, backgroundimage, synopsis, genresList);
        this.company = company;
        this.platforms=platforms;
    }

    public VideogameResponseHelper(Record record, List<Genres> genresList, List<Platforms> platforms) {
        super(record.getValue(MEDIA.MEDIAID),
                record.getValue(MEDIA.TITLE),
                record.getValue(MEDIA.RELEASEDATE),
                record.getValue(MEDIA.COVERIMAGE),
                record.getValue(MEDIA.BACKGROUNDIMAGE),
                record.getValue(MEDIA.SYNOPSIS),
                genresList);
        this.company = record.getValue(VIDEOGAMES.COMPANY);
        this.platforms = platforms;
    }
}
