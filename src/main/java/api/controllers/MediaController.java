package api.controllers;

import api.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import src.main.java.model.tables.pojos.Media;

import java.util.List;


@RestController
@RequestMapping("/api/v1/media")
public class MediaController {

    @Autowired
    MediaService mediaService;

    @GetMapping
    public List<Media> getAllMedia(){
        return mediaService.getAllMedia();
    }
}
