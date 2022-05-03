package api.Controllers;

import api.BodyResponseHelpers.MediaResponseHelper;
import api.BodyResponseHelpers.VideogameResponseHelper;
import api.Services.MediaSpecificService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/media/{id}")
public class MediaSpecificController {

    @Autowired
    MediaSpecificService mediaService;

    @GetMapping
    public MediaResponseHelper getMedia(@PathVariable Integer id){
        return mediaService.getMedia(id);
    }

}
