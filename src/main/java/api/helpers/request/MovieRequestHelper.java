package api.helpers.request;

public class MovieRequestHelper extends MediaRequestHelper {

    public MovieRequestHelper(Integer userid, Integer mediaid, String title, String releasedate, String coverimage, String backgroundimage,
                              String synopsis, Integer externalid){
        super(userid, mediaid, title, releasedate, coverimage, backgroundimage, synopsis, externalid);
    }

}
