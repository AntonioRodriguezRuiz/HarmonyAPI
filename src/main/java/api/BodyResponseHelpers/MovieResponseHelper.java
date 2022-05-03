package api.BodyResponseHelpers;

import org.jooq.Record;

import java.time.LocalDate;
import java.util.List;

import static src.main.java.HarmonyDatabase.tables.Media.MEDIA;

public class MovieResponseHelper extends MediaResponseHelper{

    public MovieResponseHelper(Integer userid, Integer mediaid, String title, LocalDate releasedate, String coverimage, String backgroundimage, String synopsis, List<src.main.java.HarmonyDatabase.tables.pojos.Genres> genresList, String company) {
        super(mediaid, title, releasedate, coverimage, backgroundimage, synopsis, genresList);
    }

    public MovieResponseHelper(Record record, List<src.main.java.HarmonyDatabase.tables.pojos.Genres> genresList) {
        super(record.getValue(MEDIA.MEDIAID),
                record.getValue(MEDIA.TITLE),
                record.getValue(MEDIA.RELEASEDATE),
                record.getValue(MEDIA.COVERIMAGE),
                record.getValue(MEDIA.BACKGROUNDIMAGE),
                record.getValue(MEDIA.SYNOPSIS),
                genresList);
    }
}
