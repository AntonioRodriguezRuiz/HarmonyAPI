package api.helpers.request;

public record ReviewRequestHelper(Integer userid, Integer reviewid,
                                  Double rating, String review) {
}
