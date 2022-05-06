package api.controllers;

import api.services.PlatformsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/platforms")
public class PlatformsController {

    @Autowired
    PlatformsService platformsService;

}
