package api.Services;

import api.BodyRequestHelpers.MediaRequest;
import api.GlobalValues;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.HarmonyDatabase.tables.pojos.Media;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static src.main.java.HarmonyDatabase.Tables.*;

@Service
public class MediaService {

    public List<Media> getAllMedia(String search, TableLike type, SortField order) {
        List<Media> result = null;

        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            if (type==null && order==null){
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .where(MEDIA.TITLE.contains(search))
                        .fetchInto(Media.class);

            } else if (order==null){
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .naturalJoin(type)
                        .where(MEDIA.TITLE.contains(search))
                        .fetchInto(Media.class);

            } else if (type==null){
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .where(MEDIA.TITLE.contains(search))
                        .orderBy(order)
                        .fetchInto(Media.class);

            } else{
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .naturalJoin(type)
                        .where(MEDIA.TITLE.contains(search))
                        .orderBy(order)
                        .fetchInto(Media.class);
            }

        } catch (Exception exception){
            exception.printStackTrace();
        }
        return result;
    }

}
