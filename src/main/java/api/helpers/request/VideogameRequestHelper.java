package api.helpers.request;

public class VideogameRequestHelper extends MediaRequestHelper{

    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public VideogameRequestHelper(Integer userid, Integer mediaid, String title, String releasedate, String coverimage, String backgroundimage,
                             String synopsis, String company){
        super(userid, mediaid, title, releasedate, coverimage, backgroundimage, synopsis);
        this.company=company;
    }

}
