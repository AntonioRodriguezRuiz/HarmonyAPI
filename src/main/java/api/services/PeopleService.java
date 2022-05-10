package api.services;

import api.helpers.request.PeopleRequestHelper;
import api.helpers.response.PeopleResponseHelper;
import database.DatabaseConnection;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.Routines;
import src.main.java.model.tables.pojos.People;

import java.sql.SQLException;
import java.util.List;

import static src.main.java.model.Tables.PEOPLE;

@Service
public class PeopleService {

    public List<People> getAllPeople(String search, Integer offset){
        List<People> peopleList=null;

        try {
            var create = DatabaseConnection.create();
            peopleList = create.select()
                                .from(PEOPLE)
                                .where(PEOPLE.NAME.contains(search))
                                .offset(offset)
                                .fetchInto(People.class);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return peopleList;
    }

    public PeopleResponseHelper postPerson(PeopleRequestHelper person) throws SQLException {
        PeopleResponseHelper newPerson=null;
        try {
            var create = DatabaseConnection.create();

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
        try {
            var create = DatabaseConnection.create();

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
