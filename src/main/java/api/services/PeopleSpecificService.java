package api.services;

import api.GlobalValues;
import api.helpers.response.GenreResponseHelper;
import api.helpers.response.PeopleResponseHelper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static src.main.java.model.Tables.PEOPLE;

@Service
public class PeopleSpecificService {
    public PeopleResponseHelper getPerson(Integer id) throws SQLException{
        PeopleResponseHelper personResult=null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)){
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Record person=create.select().from(PEOPLE).where(PEOPLE.PERSONID.eq(id)).fetch().get(0);

            personResult= new PeopleResponseHelper(person);

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return personResult;
    }

    public void deletePerson(Integer id) {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)){
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            create.deleteFrom(PEOPLE).where(PEOPLE.PERSONID.eq(id)).execute();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
