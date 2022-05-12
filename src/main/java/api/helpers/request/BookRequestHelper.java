package api.helpers.request;

public class BookRequestHelper extends MediaRequestHelper{

    private String collection;
    private Integer number;

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


    public BookRequestHelper(Integer userid, Integer mediaid, String title, String releasedate, String coverimage, String backgroundimage,
                             String synopsis, Integer externalid, String collection, Integer number) {
        super(userid, mediaid, title, releasedate, coverimage, backgroundimage, synopsis, externalid);
        this.collection=collection;
        this.number=number;
    }

}
