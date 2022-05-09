package api.helpers.response;

import org.jooq.Record;
import src.main.java.model.tables.pojos.Genres;

import java.util.List;

import static src.main.java.model.Tables.BOOKS;
import static src.main.java.model.tables.Media.MEDIA;

public class BookResponseHelper extends MediaResponseHelper{

    private String collection;

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public BookResponseHelper(Record record, List<Genres> genresList) {
        super(record.getValue(MEDIA.MEDIAID),
                record.getValue(MEDIA.TITLE),
                record.getValue(MEDIA.RELEASEDATE),
                record.getValue(MEDIA.COVERIMAGE),
                record.getValue(MEDIA.BACKGROUNDIMAGE),
                record.getValue(MEDIA.SYNOPSIS),
                record.getValue(MEDIA.AVGRATING),
                genresList);
        this.collection = record.getValue(BOOKS.COLLECTION);
    }

}
