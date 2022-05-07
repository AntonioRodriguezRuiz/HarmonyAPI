package api.controllers;

import api.helpers.request.PeopleRequestHelper;
import api.middlewares.PeopleMiddlewares;
import api.middlewares.UserMiddlewares;
import api.services.PeopleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

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
    public List<People> getAllPeople() throws SQLException {
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
        PeopleMiddlewares.existsPerson(person);
        return new ResponseEntity(peopleService.postPerson(person), HttpStatus.CREATED);
    }

    @Operation(summary = "modifies a person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "409", description = "Item already exists", content = @Content)})
    @PutMapping("/people")
    public ResponseEntity<PeopleRequestHelper> putPerson(@RequestBody PeopleRequestHelper person) throws SQLException {
        UserMiddlewares.isAdmin(person.getPersonid());
        if(person.getPersonid()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        PeopleMiddlewares.DoesNotExistsPerson(person.getPersonid());
        peopleService.putPerson(person);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
