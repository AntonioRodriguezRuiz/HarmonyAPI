package api.controllers;

import api.helpers.response.MediaResponseHelper;
import api.services.MediaSpecificService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
