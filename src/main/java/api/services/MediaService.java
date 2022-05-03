package api.services;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import src.main.java.harmony.tables.pojos.Media;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static src.main.java.harmony.Tables.MEDIA;

@Service
public class MediaService {

    public List<Media> getAllMedia(){
        String userName = "user";
        String password = "user";
        String url = "jdbc:mariadb://localhost:3306/harmony";
        DSLContext create;
        List<Media> result = null;

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
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
