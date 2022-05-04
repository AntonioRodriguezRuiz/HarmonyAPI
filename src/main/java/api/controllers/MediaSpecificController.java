package api.controllers;

import api.helpers.request.*;
import api.helpers.response.*;
import api.middlewares.UserMiddlewares;
import api.services.MediaSpecificService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

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
    public ResponseEntity<PlatformResponseHelper> addPlatform(@PathVariable Integer id, @RequestBody PlatformRequestHelper platform) throws SQLException {
        UserMiddlewares.isAdmin(platform.getUserid());
        if(platform.getPlatformid()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mediaService.addPlatform(id, platform), HttpStatus.CREATED);
    }

    @DeleteMapping("/platforms/{platformid}")
    public ResponseEntity removePlatform(@PathVariable Integer id, @PathVariable Integer platformid, @RequestBody UseridBodyHelper user) throws SQLException {
        UserMiddlewares.isAdmin(user.userid());
        mediaService.removePlatform(id, platformid);
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

    @PostMapping("/genres")
    public ResponseEntity<GenreResponseHelper> addGenre(@PathVariable Integer id, @RequestBody GenreRequestHelper genre) throws SQLException {
        UserMiddlewares.isAdmin(genre.getUserid());
        if(genre.getGenreid()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mediaService.addGenre(id, genre), HttpStatus.CREATED);
    }

    @DeleteMapping("/genres/{genreid}")
    public ResponseEntity removeGenre(@PathVariable Integer id, @PathVariable Integer genreid, @RequestBody UseridBodyHelper user) throws SQLException {
        UserMiddlewares.isAdmin(user.userid());
        mediaService.removeGenre(id, genreid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/people")
    public List<PeopleMediaResponseHelper> getPeopleFromMedia(@PathVariable Integer id) throws SQLException {
        return mediaService.getPeopleFromMedia(id);
    }

    @PostMapping("/people")
    public ResponseEntity<PeopleMediaResponseHelper> addPerson(@PathVariable Integer id, @RequestBody PeopleMediaRequestHelper person) throws SQLException {
        UserMiddlewares.isAdmin(person.getUserid());
        if(person.getPersonid()==null || person.getRole()==null || person.getRoleType()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mediaService.addPerson(id, person), HttpStatus.CREATED);
    }

    @DeleteMapping("/people/{personid}")
    public ResponseEntity removePerson(@PathVariable Integer id, @PathVariable Integer personid, @RequestBody UseridBodyHelper user) throws SQLException {
        UserMiddlewares.isAdmin(user.userid());
        mediaService.removePerson(id, personid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{seasonid}/{episodeid}/people")
    public List<PeopleMediaResponseHelper> getPeopleFromEpisode(@PathVariable Integer id, @PathVariable Integer seasonid, @PathVariable Integer episodeid) throws SQLException {
        return mediaService.getPeopleFromEpisode(id, seasonid, episodeid);
    }

    @PostMapping("/{seasonid}/{episodeid}/people")
    public ResponseEntity<PeopleMediaResponseHelper> addPersonEpisode(@PathVariable Integer id, @PathVariable Integer seasonid, @PathVariable Integer episodeid, @RequestBody PeopleEpisodeRequestHelper person) throws SQLException {
        UserMiddlewares.isAdmin(person.getUserid());
        if(person.getPersonid()==null || person.getRole()==null || person.getRoleType()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mediaService.addPersonEpisode(id, seasonid, episodeid, person), HttpStatus.CREATED);
    }

    @DeleteMapping("/{seasonid}/{episodeid}/people/{personid}")
    public ResponseEntity removePersonEpisode(@PathVariable Integer id, @PathVariable Integer seasonid, @PathVariable Integer episodeid, @PathVariable Integer personid, @RequestBody UseridBodyHelper user) throws SQLException {
        UserMiddlewares.isAdmin(user.userid());
        mediaService.removePersonEpisode(id, seasonid, episodeid, personid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/reviews")
    public List<ReviewResponseHelper> getReviews(@PathVariable Integer id) throws SQLException {
        return mediaService.getReviews(id);
    }

    @PostMapping("/reviews")
    public ReviewResponseHelper addReview(@PathVariable Integer id, @RequestBody ReviewRequestHelper review) throws SQLException {
        if(review.userid()==null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        if(review.rating()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return mediaService.addReview(id, review);
    }

    @PutMapping("/reviews")
    public ResponseEntity putReview(@PathVariable Integer id, @RequestBody ReviewRequestHelper review) throws SQLException {
        if(review.userid()==null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else if(review.reviewid()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        UserMiddlewares.isOwnerOfReview(review.userid(), review.reviewid());
        if(review.rating()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        mediaService.putReview(id, review);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
