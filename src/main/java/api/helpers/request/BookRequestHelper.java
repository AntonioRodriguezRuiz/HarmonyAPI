package api.helpers.request;

public class BookRequestHelper extends MediaRequestHelper{

    private String collection;

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public BookRequestHelper(Integer userid, Integer mediaid, String title, String releasedate, String coverimage, String backgroundimage,
                             String synopsis, Integer externalid, String collection){
        super(userid, mediaid, title, releasedate, coverimage, backgroundimage, synopsis, externalid);
        this.collection=collection;
    }

}
