package api.helpers.response;

import org.jooq.Record;
import src.main.java.model.tables.pojos.Genres;
import src.main.java.model.tables.pojos.Seasons;

import java.util.List;

import static src.main.java.model.tables.Media.MEDIA;

public class SeriesResponseHelper extends MediaResponseHelper{

    private Integer noSeasons;
    private List<Seasons> seasons;

    public Integer getNoSeasons() {
        return noSeasons;
    }

    public void setNoSeasons(Integer noSeasons) {
        this.noSeasons = noSeasons;
    }

    public SeriesResponseHelper(Record record, List<Genres> genresList, Integer noSeasons, List<Seasons> seasons) {
        super(record.getValue(MEDIA.MEDIAID),
                record.getValue(MEDIA.TITLE),
                record.getValue(MEDIA.RELEASEDATE),
                record.getValue(MEDIA.COVERIMAGE),
                record.getValue(MEDIA.BACKGROUNDIMAGE),
                record.getValue(MEDIA.SYNOPSIS),
                record.getValue(MEDIA.AVGRATING),
                genresList);
        this.noSeasons = noSeasons;
        this.seasons = seasons;
    }

    public List<Seasons> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Seasons> seasons) {
        this.seasons = seasons;
    }
}
