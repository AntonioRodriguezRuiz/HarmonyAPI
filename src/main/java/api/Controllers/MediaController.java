package api.Controllers;

import api.Services.MediaService;
import org.jooq.OrderField;
import org.jooq.SortField;
import org.jooq.TableField;
import org.jooq.TableLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.HarmonyDatabase.Tables;
import src.main.java.HarmonyDatabase.tables.pojos.Media;

import java.util.List;
import java.util.Map;

import static src.main.java.HarmonyDatabase.Tables.*;


@RestController
@RequestMapping("/api/v1/media")
public class MediaController {

    @Autowired
    MediaService mediaService;

    @GetMapping
    public List<Media> getAllMedia(@RequestParam Map<String, String> allRequestParams) {
        String search = allRequestParams.containsKey("search") ? allRequestParams.get("search") : "";

        String typeString = allRequestParams.containsKey("type") ? allRequestParams.get("type") : null;
        TableLike typeTable = null;

        String orderString =  allRequestParams.containsKey("order") ? allRequestParams.get("order") : null;
        SortField orderField = null;

        if(typeString!=null){
            switch (typeString){
                case "movie": typeTable = Tables.MOVIES; break;
                case "series": typeTable = Tables.SERIES; break;
                case "videogame": typeTable = Tables.VIDEOGAMES; break;
                case "book": typeTable = Tables.BOOKS; break;
                default: throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }

        if(orderString!=null){
            switch (orderString){
                case "title": orderField = MEDIA.TITLE.asc(); break;
                case "releaseDate": orderField = MEDIA.RELEASEDATE.desc(); break;
                case "rating": orderField = MEDIA.AVGRATING.desc(); break;
                default: throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }

        String order = allRequestParams.containsKey("order") ? allRequestParams.get("order") : null;

        return mediaService.getAllMedia(search, typeTable, orderField);
    }
}
