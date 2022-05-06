package api.helpers.response;

public class PlatformResponseHelper {
    private Integer platformid;
    private String name;

    public Integer getPlatformid() {
        return platformid;
    }

    public void setPlatformid(Integer platformid) {
        this.platformid = platformid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlatformResponseHelper(Integer platformid, String name) {
        this.platformid = platformid;
        this.name = name;
    }
}
