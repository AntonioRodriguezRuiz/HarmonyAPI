package api.helpers.response;

import org.jooq.Record;

import java.util.List;

import static src.main.java.model.tables.Media.MEDIA;
import src.main.java.model.tables.pojos.Genres;

public class MovieResponseHelper extends MediaResponseHelper{

    public MovieResponseHelper(Record record, List<Genres> genresList) {
        super(record.getValue(MEDIA.MEDIAID),
                record.getValue(MEDIA.TITLE),
                record.getValue(MEDIA.RELEASEDATE),
                record.getValue(MEDIA.COVERIMAGE),
                record.getValue(MEDIA.BACKGROUNDIMAGE),
                record.getValue(MEDIA.SYNOPSIS),
                record.getValue(MEDIA.AVGRATING),
                genresList);
    }
}
