package api.helpers.response;
import static src.main.java.model.Tables.*;
import org.jooq.Record;

public record ReportResponseHelper(Integer reportid, Integer useridreporter, Integer useridreported,
                                   Integer reviewid, String reason) {

    public static ReportResponseHelper of(Record record){
        return new ReportResponseHelper(
                record.getValue(REPORTS.REPORTID),
                record.getValue(REPORTS.USERIDREPORTER),
                record.getValue(REPORTS.USERIDREPORTED),
                record.getValue(REPORTS.REVIEWID),
                record.getValue(REPORTS.REASON)
                );
    }
}
