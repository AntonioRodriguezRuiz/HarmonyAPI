package api.helpers.response;

import api.helpers.enums.RoleType;
import org.jooq.Record;
import org.jooq.Table;

import java.time.LocalDate;

public class PeopleEpisodeResponseHelper extends PeopleMediaResponseHelper{

    private Integer seasonid;
    private Integer episodeid;

    public Integer getSeasonid() {
        return seasonid;
    }

    public void setSeasonid(Integer seasonid) {
        this.seasonid = seasonid;
    }

    public Integer getEpisodeid() {
        return episodeid;
    }

    public void setEpisodeid(Integer episodeid) {
        this.episodeid = episodeid;
    }

    public PeopleEpisodeResponseHelper(Integer mediaid, Integer personid, String name, LocalDate birthdate, String role, RoleType roleType, Integer seasonid, Integer episodeid) {
        super(mediaid, personid, name, birthdate, role, roleType);
        this.seasonid = seasonid;
        this.episodeid = episodeid;
    }

    public PeopleEpisodeResponseHelper(Record record, Table type, Integer seasonid, Integer episodeid) {
        super(record, type);
        this.seasonid = seasonid;
        this.episodeid = episodeid;
    }
}
