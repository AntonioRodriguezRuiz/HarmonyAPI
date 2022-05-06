package api.controllers;

import api.helpers.request.PlatformRequestHelper;
import api.helpers.response.PlatformResponseHelper;
import api.middlewares.UserMiddlewares;
import api.services.PlatformsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.main.java.model.tables.pojos.Platforms;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/platforms")
public class PlatformsController {

    @Autowired
    PlatformsService platformsService;

    @Operation(summary = "Get all the platforms for videogames")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetch successful")})
    @GetMapping
    public List<Platforms> getAllPlatforms(){
        return platformsService.getAllPlatforms();
    }

    @Operation(summary = "Adds a new videogame platform")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "409", description = "This platform is already registered", content = @Content)})
    @PostMapping
    public ResponseEntity<PlatformResponseHelper> postPlatform(@RequestBody PlatformRequestHelper platform) throws SQLException {
        UserMiddlewares.isAdmin(platform.getUserid());
        return new ResponseEntity<>(platformsService.postPlatform(platform), HttpStatus.CREATED);
    }
}
