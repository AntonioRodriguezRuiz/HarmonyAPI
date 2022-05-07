package api.controllers;

import api.GlobalValues;
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
import src.main.java.model.tables.pojos.People;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleController {

    @Autowired
    PeopleService peopleService;

    @Operation(summary = "Get all persons and sort by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search successful")})
    @GetMapping
    public List<People> getAllPeople(@RequestParam(name="search", required = false) String searchParam,
                                     @RequestParam(name="page", required = false) String pageParam) throws SQLException {

        String search = searchParam!=null ? searchParam : "";

        Integer page = pageParam!=null ? Integer.valueOf(pageParam) : 1;
        Integer offset = page * GlobalValues.PAGE_SIZE - GlobalValues.PAGE_SIZE;

        return peopleService.getAllPeople(search, offset);

    }

    @Operation(summary = "Post a new person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "409", description = "Item already exists", content = @Content)})
    @PostMapping
    public ResponseEntity postPerson (@RequestBody PeopleRequestHelper person) throws SQLException {
        UserMiddlewares.isAdmin(person.getUserid());
        if(person.getName()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        PeopleMiddlewares.doesNotExistsPerson(person);
        return new ResponseEntity(peopleService.postPerson(person), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifies a person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "409", description = "Item already exists", content = @Content)})
    @PutMapping("/people")
    public ResponseEntity<PeopleRequestHelper> putPerson(@RequestBody PeopleRequestHelper person) throws SQLException {
        UserMiddlewares.isAdmin(person.getUserid());
        if(person.getPersonid()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        PeopleMiddlewares.doesNotExistsPerson(person.getPersonid());
        peopleService.putPerson(person);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
