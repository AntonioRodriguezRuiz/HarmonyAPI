package api.BodyResponseHelpers;

import org.jooq.Record;
import src.main.java.HarmonyDatabase.tables.pojos.Genres;

import java.time.LocalDate;
import java.util.List;

import static src.main.java.HarmonyDatabase.tables.Media.MEDIA;

public class SeriesResponseHelper extends MediaResponseHelper{

    private Integer noSeasons;

    public Integer getNoSeasons() {
        return noSeasons;
    }

    public void setNoSeasons(Integer noSeasons) {
        this.noSeasons = noSeasons;
    }

    public SeriesResponseHelper(Integer userid, Integer mediaid, String title, LocalDate releasedate, String coverimage, String backgroundimage, String synopsis, List<Genres> genresList, String company, Integer noSeasons) {
        super(mediaid, title, releasedate, coverimage, backgroundimage, synopsis, genresList);
        this.noSeasons = noSeasons;
    }

    public SeriesResponseHelper(Record record, List<Genres> genresList, Integer noSeasons) {
        super(record.getValue(MEDIA.MEDIAID),
                record.getValue(MEDIA.TITLE),
                record.getValue(MEDIA.RELEASEDATE),
                record.getValue(MEDIA.COVERIMAGE),
                record.getValue(MEDIA.BACKGROUNDIMAGE),
                record.getValue(MEDIA.SYNOPSIS),
                genresList);
        this.noSeasons = noSeasons;
    }

}
