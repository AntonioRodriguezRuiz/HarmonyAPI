package api.services;

import api.GlobalValues;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static src.main.java.model.Tables.MEDIA;
import static src.main.java.model.Tables.REPORTS;

@Service
public class ReportService {
    private Result<Record> existsReport(Integer id) throws SQLException{
        Result<Record> reportList = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            reportList = create.select()
                    .from(REPORTS)
                    .where(REPORTS.REPORTID.eq(id))
                    .fetch();


        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return reportList;
    }

    public void deleteReport(Integer id){
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Result<Record> reportList = existsReport(id);

            if(reportList.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            create.deleteFrom(REPORTS)
                    .where(REPORTS.REPORTID.eq(id)).execute();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
