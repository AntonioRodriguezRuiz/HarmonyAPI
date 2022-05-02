package api.Services;

import api.GlobalValues;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.SortField;
import org.jooq.TableLike;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import src.main.java.HarmonyDatabase.tables.pojos.Media;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static src.main.java.HarmonyDatabase.Tables.*;

@Service
public class MediaService {

    public List<Media> getAllMedia(String search, TableLike type, SortField order, String genre, Integer offset) {
        List<Media> result = null;

        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            if (type==null && order==null && genre==null){
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .where(MEDIA.TITLE.contains(search))
                        .offset(offset)
                        .limit(GlobalValues.PAGE_SIZE)
                        .fetchInto(Media.class);

            } else if (order==null && genre==null){
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .naturalJoin(type)
                        .where(MEDIA.TITLE.contains(search))
                        .offset(offset)
                        .limit(GlobalValues.PAGE_SIZE)
                        .fetchInto(Media.class);

            } else if (type==null && order==null){
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .naturalJoin(MEDIAGENRES)
                        .naturalJoin(GENRES)
                        .where(MEDIA.TITLE.contains(search).and(GENRES.NAME.eq(genre)))
                        .offset(offset)
                        .limit(GlobalValues.PAGE_SIZE)
                        .fetchInto(Media.class);

            } else if (type==null && genre==null){
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .where(MEDIA.TITLE.contains(search))
                        .orderBy(order)
                        .offset(offset)
                        .limit(GlobalValues.PAGE_SIZE)
                        .fetchInto(Media.class);

            } else if (genre==null){
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .naturalJoin(type)
                        .where(MEDIA.TITLE.contains(search))
                        .orderBy(order)
                        .offset(offset)
                        .limit(GlobalValues.PAGE_SIZE)
                        .fetchInto(Media.class);

            } else if (type==null){
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .naturalJoin(MEDIAGENRES)
                        .naturalJoin(GENRES)
                        .where(MEDIA.TITLE.contains(search).and(GENRES.NAME.eq(genre)))
                        .orderBy(order)
                        .offset(offset)
                        .limit(GlobalValues.PAGE_SIZE)
                        .fetchInto(Media.class);

            } else if (order==null){
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .naturalJoin(MEDIAGENRES)
                        .naturalJoin(GENRES)
                        .naturalJoin(type)
                        .where(MEDIA.TITLE.contains(search).and(GENRES.NAME.eq(genre)))
                        .offset(offset)
                        .limit(GlobalValues.PAGE_SIZE)
                        .fetchInto(Media.class);
            } else{
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .naturalJoin(MEDIAGENRES)
                        .naturalJoin(GENRES)
                        .naturalJoin(type)
                        .where(MEDIA.TITLE.contains(search).and(GENRES.NAME.eq(genre)))
                        .orderBy(order)
                        .offset(offset)
                        .limit(GlobalValues.PAGE_SIZE)
                        .fetchInto(Media.class);
            }

        } catch (Exception exception){
            exception.printStackTrace();
        }
        return result;
    }

}
