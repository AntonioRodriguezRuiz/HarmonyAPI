package api.helpers.request;

public record ReportRequestHelper(Integer useridporter, Integer useridreported,
                                  Integer reviewid, String reason) {
}
