package api.services;

import api.GlobalValues;
import api.helpers.request.*;
import api.helpers.response.PeopleResponseHelper;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.People;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import src.main.java.model.Routines;

import static src.main.java.model.Tables.*;

@Service
public class PeopleService {

    public List<People> getAllPeople(){
        List<People> peopleList=null;

        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            peopleList=create.select().from(PEOPLE).orderBy(PEOPLE.PERSONID).fetchInto(People.class);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return peopleList;
    }

    public PeopleResponseHelper postPerson(PeopleRequestHelper person) throws SQLException {
        PeopleResponseHelper newPerson=null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Routines.newperson(create.configuration(), person.getName(), person.getBirthdate(), person.getPicture());

            Integer newPersonID=create.select().from(PEOPLE).orderBy(PEOPLE.PERSONID.desc()).limit(1).fetch().
                    get(0).get(PEOPLE.PERSONID);

            PeopleSpecificService peopleSpecificService = new PeopleSpecificService();
            newPerson = peopleSpecificService.getPerson(newPersonID);

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return newPerson;
    }

    public void putPerson(PeopleRequestHelper person) throws SQLException{
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            People oldPerson = create.select()
                                    .from(PEOPLE)
                                    .where(PEOPLE.PERSONID.eq(person.getPersonid()))
                                    .fetchInto(People.class).get(0);

            person.setName(person.getName()==null ? oldPerson.getName() : person.getName());
            person.setBirthdate(person.getBirthdate()==null ? oldPerson.getBirthdate() : person.getBirthdate());
            person.setPicture(person.getPicture()==null ? oldPerson.getPicture() : person.getPicture());

            create.update(PEOPLE)
                    .set(PEOPLE.NAME, person.getName())
                    .set(PEOPLE.BIRTHDATE, person.getBirthdate())
                    .set(PEOPLE.PICTURE, person.getPicture())
                    .where(PEOPLE.PERSONID.eq(person.getPersonid()))
                    .execute();

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }
}
