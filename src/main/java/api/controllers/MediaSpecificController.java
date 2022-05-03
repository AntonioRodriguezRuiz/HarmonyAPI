package api.controllers;

import api.helpers.request.PlatformRequestHelper;
import api.helpers.request.SeasonRequestHelper;
import api.helpers.response.MediaResponseHelper;
import api.helpers.response.PlatformResponseHelper;
import api.helpers.response.SeasonResponseHelper;
import api.middlewares.UserMiddlewares;
import api.services.MediaSpecificService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/media/{id}")
public class MediaSpecificController {

    @Autowired
    MediaSpecificService mediaService;

    @GetMapping
    public MediaResponseHelper getMedia(@PathVariable Integer id) throws SQLException {
        return mediaService.getMedia(id);
    }

    @DeleteMapping
    public ResponseEntity deleteMedia(@PathVariable Integer id, @RequestBody String userid) throws SQLException {
        UserMiddlewares.isAdmin(Integer.valueOf(userid.split(":")[1].split("\n}")[0].strip()));
        mediaService.deleteMedia(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/seasons")
    public ResponseEntity<SeasonResponseHelper> postSeason(@PathVariable Integer id, @RequestBody SeasonRequestHelper season) throws SQLException {
        UserMiddlewares.isAdmin(season.getUserid());
        if(season.getSeasonNo()==null || season.getNoEpisodes()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mediaService.postSeason(id, season), HttpStatus.CREATED);
    }

    @PutMapping("/seasons")
    public ResponseEntity putSeason(@PathVariable Integer id, @RequestBody SeasonRequestHelper season) throws SQLException {
        UserMiddlewares.isAdmin(season.getUserid());
        if(season.getSeasonid()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        mediaService.putSeason(id, season);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/platforms")
    public ResponseEntity<PlatformResponseHelper> postPlatform(@PathVariable Integer id, @RequestBody PlatformRequestHelper platform) throws SQLException {
        UserMiddlewares.isAdmin(platform.getUserid());
        if(platform.getPlatformid()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mediaService.postPlatform(id, platform), HttpStatus.CREATED);
    }

    @DeleteMapping("/platforms")
    public ResponseEntity deletePlatform(@PathVariable Integer id, @RequestBody PlatformRequestHelper platform) throws SQLException {
        UserMiddlewares.isAdmin(platform.getUserid());
        if(platform.getPlatformid()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        mediaService.deletePlatform(id, platform);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
