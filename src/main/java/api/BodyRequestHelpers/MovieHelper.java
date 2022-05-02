package api.BodyRequestHelpers;

import java.time.LocalDate;

public class MovieHelper extends MediaHelper{

    public MovieHelper(Integer userid, Integer mediaid, String title, String releasedate, String coverimage, String backgroundimage,
                       String synopsis){
        super(userid, mediaid, title, releasedate, coverimage, backgroundimage, synopsis);
    }

    public MovieHelper(){
        super();
    }
}
