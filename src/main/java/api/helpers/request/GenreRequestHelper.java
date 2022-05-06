package api.helpers.request;

public class GenreRequestHelper {
    private Integer userid;
    private Integer genreid;
    private String name;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

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

    public GenreRequestHelper(Integer userid, Integer genreid, String name) {
        this.userid = userid;
        this.genreid = genreid;
        this.name = name;
    }
}
