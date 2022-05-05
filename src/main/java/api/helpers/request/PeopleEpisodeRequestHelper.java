package api.helpers.request;

import api.helpers.enums.RoleType;

public class PeopleEpisodeRequestHelper extends PeopleMediaRequestHelper{
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

    public PeopleEpisodeRequestHelper(Integer userid, Integer personid, String role, RoleType roleType, Integer seasonid, Integer episodeid) {
        super(userid, personid, role, roleType);
        this.seasonid = seasonid;
        this.episodeid = episodeid;

    }
}
