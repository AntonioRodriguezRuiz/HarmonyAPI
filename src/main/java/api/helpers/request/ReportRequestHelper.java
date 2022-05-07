package api.helpers.request;

public record ReportRequestHelper(Integer useridreporter, Integer useridreported,
                                  Integer reviewid, String reason) {
}
