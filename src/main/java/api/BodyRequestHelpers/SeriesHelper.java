package api.BodyRequestHelpers;

public class SeriesHelper extends MediaHelper {
    public SeriesHelper(Integer userid, Integer mediaid, String title, String releasedate, String coverimage, String backgroundimage, String synopsis) {
        super(userid, mediaid, title, releasedate, coverimage, backgroundimage, synopsis);
    }

    public SeriesHelper(){
        super();
    }
}
