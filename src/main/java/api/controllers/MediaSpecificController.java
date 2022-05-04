package api.controllers;

import api.helpers.request.EpisodeRequestHelper;
import api.helpers.request.PlatformRequestHelper;
import api.helpers.request.SeasonRequestHelper;
import api.helpers.request.UseridBodyHelper;
import api.helpers.response.EpisodeResponseHelper;
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
    public ResponseEntity deleteMedia(@PathVariable Integer id, @RequestBody UseridBodyHelper useridBody) throws SQLException {
        UserMiddlewares.isAdmin(useridBody.userid());
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

    @DeleteMapping("/platforms/{platformid}")
    public ResponseEntity deletePlatform(@PathVariable Integer id, @PathVariable Integer platformid, @RequestBody UseridBodyHelper user) throws SQLException {
        UserMiddlewares.isAdmin(user.userid());
        mediaService.deletePlatform(id, platformid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{seasonid}")
    public SeasonResponseHelper getSeason(@PathVariable Integer id, @PathVariable Integer seasonid) throws SQLException {
        return mediaService.getSeason(id,seasonid);
    }

    @PostMapping("/{seasonid}")
    public ResponseEntity<EpisodeResponseHelper> postEpisode(@PathVariable Integer id, @PathVariable Integer seasonid, @RequestBody EpisodeRequestHelper episode) throws SQLException {
        UserMiddlewares.isAdmin(episode.getUserid());
        if(episode.getEpisodeNo()==null || episode.getEpisodeName()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mediaService.postEpisode(id, seasonid, episode), HttpStatus.CREATED);
    }

    @PutMapping("/{seasonid}")
    public ResponseEntity putEpisode(@PathVariable Integer id, @PathVariable Integer seasonid, @RequestBody EpisodeRequestHelper episode) throws SQLException {
        UserMiddlewares.isAdmin(episode.getUserid());
        if(episode.getEpisodeid()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        mediaService.putEpisode(id, seasonid, episode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{seasonid}")
    public ResponseEntity deleteSeason(@PathVariable Integer id, @PathVariable Integer seasonid, @RequestBody UseridBodyHelper user) throws SQLException {
        UserMiddlewares.isAdmin(user.userid());
        mediaService.deleteSeason(id, seasonid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{seasonid}/{episodeid}")
    public EpisodeResponseHelper getEpisode(@PathVariable Integer id, @PathVariable Integer seasonid, @PathVariable Integer episodeid) throws SQLException {
        return mediaService.getEpisode(id,seasonid, episodeid);
    }

    @DeleteMapping("/{seasonid}/{episodeid}")
    public ResponseEntity deleteEpisode(@PathVariable Integer id, @PathVariable Integer seasonid, @PathVariable Integer episodeid, @RequestBody UseridBodyHelper user) throws SQLException {
        UserMiddlewares.isAdmin(user.userid());
        mediaService.deleteEpisode(id, seasonid, episodeid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
