package api.helpers.response;

import org.jooq.Record;
import src.main.java.model.tables.pojos.Media;

import java.time.LocalDateTime;
import java.util.List;

import static src.main.java.model.Tables.LISTS;

/**
 * ListResponseHelper
 * Project HarmonyAPI
 * Created: 2022-05-06
 *
 * @author juagallop1
 **/
public record ListResponseHelper(Integer listId, Integer userId, String listName, String icon, LocalDateTime creationDate, LocalDateTime modificationDate, List<Media> media) {
    public ListResponseHelper(Record list, List<Media> media) {
        this(
            list.get(LISTS.LISTID),
            list.get(LISTS.USERID),
            list.get(LISTS.LISTNAME),
            list.get(LISTS.ICON),
            list.get(LISTS.CREATIONDATE),
            list.get(LISTS.MODIFICATIONDATE),
            media
        );
    }
}
