package api.services;

import api.helpers.response.PeopleResponseHelper;
import database.DatabaseConnection;
import org.jooq.Record;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

import static src.main.java.model.Tables.PEOPLE;

@Service
public class PeopleSpecificService {
    public PeopleResponseHelper getPerson(Integer id) throws SQLException{
        PeopleResponseHelper personResult=null;
        try {
            var create = DatabaseConnection.create();

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
        try {
            var create = DatabaseConnection.create();

            create.deleteFrom(PEOPLE).where(PEOPLE.PERSONID.eq(id)).execute();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
