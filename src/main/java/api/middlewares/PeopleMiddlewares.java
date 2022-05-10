package api.middlewares;

import api.helpers.request.PeopleRequestHelper;
import database.DatabaseConnection;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

import static src.main.java.model.Tables.PEOPLE;

public class PeopleMiddlewares {

    public static void existsPerson(Integer personid) throws SQLException {
        try {
            var create = DatabaseConnection.create();

            if(create.select()
                    .from(PEOPLE)
                    .where(PEOPLE.PERSONID.eq(personid))
                    .fetch()
                    .isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person does not exist");
            }

        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void doesNotExistsPerson(PeopleRequestHelper person) throws SQLException {
        try {
            var create = DatabaseConnection.create();

            if(person.getBirthdate()!=null && !create.select()
                    .from(PEOPLE)
                    .where(PEOPLE.NAME.eq(person.getName())
                            .and(PEOPLE.BIRTHDATE.eq(person.getBirthdate())))
                    .fetch()
                    .isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Person already exists");

            } else if(!create.select()
                    .from(PEOPLE)
                    .where(PEOPLE.NAME.eq(person.getName())
                            .and(PEOPLE.BIRTHDATE.isNull()))
                    .fetch()
                    .isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Person already exists");
            }
        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }
}
