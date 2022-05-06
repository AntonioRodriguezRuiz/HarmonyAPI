package api.controllers;

import api.services.PlatformsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import src.main.java.model.tables.pojos.Platforms;

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

}
