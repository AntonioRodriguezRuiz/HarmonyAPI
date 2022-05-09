package api.helpers.response;

import org.jooq.Record;
import src.main.java.model.tables.pojos.Genres;
import src.main.java.model.tables.pojos.Platforms;

import java.util.List;

import static src.main.java.model.Tables.VIDEOGAMES;
import static src.main.java.model.tables.Media.MEDIA;
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

    public VideogameResponseHelper(Record record, List<Genres> genresList, List<Platforms> platforms) {
        super(record.getValue(MEDIA.MEDIAID),
                record.getValue(MEDIA.TITLE),
                record.getValue(MEDIA.RELEASEDATE),
                record.getValue(MEDIA.COVERIMAGE),
                record.getValue(MEDIA.BACKGROUNDIMAGE),
                record.getValue(MEDIA.SYNOPSIS),
                record.getValue(MEDIA.AVGRATING),
                genresList);
        this.company = record.getValue(VIDEOGAMES.COMPANY);
        this.platforms = platforms;
    }
}
