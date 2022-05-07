package api.controllers;

import api.GlobalValues;
import api.helpers.request.BookRequestHelper;
import api.helpers.request.GenreRequestHelper;
import api.helpers.request.PeopleRequestHelper;
import api.helpers.response.MediaResponseHelper;
import api.middlewares.UserMiddlewares;
import api.services.PeopleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.SortField;
import org.jooq.TableLike;
import org.jooq.impl.DSL;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.Media;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static src.main.java.model.tables.People.PEOPLE;
import static src.main.java.model.tables.Media.MEDIA;
import src.main.java.model.tables.pojos.People;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleController {

    @Autowired
    PeopleService peopleService;

    @Operation(summary = "Get all persons and sort by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search successful")})
    @GetMapping
    public List<src.main.java.model.tables.pojos.People> getAllPeople() throws SQLException {
        return peopleService.getAllPeople();

    }

    @Operation(summary = "post a new person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "409", description = "Item already exists", content = @Content)})
    @PostMapping
    public ResponseEntity postPerson (@RequestBody PeopleRequestHelper person) throws SQLException {
        UserMiddlewares.isAdmin(person.getPersonid());
        if(person.getName()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(peopleService.postPerson(person), HttpStatus.CREATED);
    }

}
