package api.helpers.response;

import org.jooq.Record;

import static src.main.java.model.Tables.GENRES;

public class GenreResponseHelper {
    private Integer genreid;
    private String name;

    public Integer getGenreid() {
        return genreid;
    }

    public void setGenreid(Integer genreid) {
        this.genreid = genreid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenreResponseHelper(Integer genreid, String name) {
        this.genreid = genreid;
        this.name = name;
    }

    public GenreResponseHelper(Record record){
        this.genreid = record.getValue(GENRES.GENREID);
        this.name = record.get(GENRES.NAME);
    }
}
