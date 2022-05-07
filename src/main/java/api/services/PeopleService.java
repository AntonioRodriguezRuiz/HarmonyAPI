package api.services;

import api.GlobalValues;
import api.helpers.request.*;
import api.helpers.response.PeopleResponseHelper;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.People;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import src.main.java.model.Routines;

import static src.main.java.model.Tables.*;
import static src.main.java.model.Tables.GENRES;

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

            Routines.newperson(create.configuration(), newPerson.getName(), newPerson.getBirthdate(), newPerson.getPicture());

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

    public void putPerson(PeopleRequestHelper people, People oldperson) throws SQLException{
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            PeopleRequestHelper newPerson=new PeopleRequestHelper(null,null,null,null);
            newPerson.setPersonid(people.getPersonid());

            newPerson.setName(people.getName() != null ? people.getName() : oldperson.getName());
            newPerson.setBirthdate(people.getBirthdate() != null ? people.getBirthdate() : oldperson.getBirthdate());
            newPerson.setPicture(people.getPicture() != null ? people.getPicture() : oldperson.getPicture());

            create.update(PEOPLE)
                    .set(PEOPLE.NAME, newPerson.getName())
                    .set(PEOPLE.BIRTHDATE, newPerson.getBirthdate())
                    .set(PEOPLE.PICTURE, newPerson.getPicture())
                    .where(PEOPLE.PERSONID.eq(newPerson.getPersonid()))
                    .execute();

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }
}
