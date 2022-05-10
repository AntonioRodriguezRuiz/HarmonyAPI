package api.helpers.request;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VideogameRequestHelper extends MediaRequestHelper{

    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public VideogameRequestHelper(Integer userid, Integer mediaid, String title, String releasedate, String coverimage, String backgroundimage,
                                  String synopsis, Integer externalid, String company){
        super(userid, mediaid, title, releasedate, coverimage, backgroundimage, synopsis, externalid);
        this.company=company;
    }

    @Override
    public void validate() {
        super.validate();
        if(company == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Company is required");
        }
    }

}
