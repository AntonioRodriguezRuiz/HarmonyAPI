package api.controllers;

import api.helpers.response.PeopleResponseHelper;
import api.helpers.request.PeopleRequestHelper;
import api.GlobalValues;
import api.helpers.enums.RoleType;
import api.helpers.request.*;
import api.helpers.response.*;
import api.middlewares.UserMiddlewares;
import api.services.GenresSpecificService;
import api.services.PeopleSpecificService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static src.main.java.model.Tables.*;
import src.main.java.model.Routines;
import src.main.java.model.tables.pojos.People;

@RestController
@RequestMapping("/api/v1/people/{id}")
public class PeopleSpecificController {

    @Autowired
    PeopleSpecificService peopleSpecificService;

    @Operation(summary = "Get details about a certain person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search successful"),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @GetMapping
    public PeopleResponseHelper getPerson(@PathVariable Integer id) throws SQLException {
        return peopleSpecificService.getPerson(id);
    }

    @Operation(summary = "Deletes a person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item destroyed"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @DeleteMapping
    public ResponseEntity deleteperson(@PathVariable Integer id, @RequestBody UseridBodyHelper useridBody) throws SQLException {
        UserMiddlewares.isAdmin(useridBody.userid());
        peopleSpecificService.deletePerson(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
