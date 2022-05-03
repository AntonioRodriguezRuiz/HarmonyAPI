package api.services;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import src.main.java.model.tables.pojos.Media;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static api.GlobalValues.*;
import static src.main.java.model.Tables.MEDIA;

@Service
public class MediaService {

    public List<Media> getAllMedia(){
        DSLContext create;
        List<Media> result = null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            create = DSL.using(conn, SQLDialect.MARIADB);
            result = create.select()
                    .from(MEDIA)
                    .fetchInto(Media.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
