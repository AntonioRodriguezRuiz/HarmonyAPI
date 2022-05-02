package api.Middlewares;

import api.GlobalValues;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static src.main.java.HarmonyDatabase.Tables.ADMINS;
import src.main.java.HarmonyDatabase.tables.pojos.Admins;

public class UserMiddlewares {

    public static void isAdmin(Integer userid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            List<Admins> result = create.select()
                    .from(ADMINS)
                    .where(ADMINS.USERID.eq(userid))
                    .fetchInto(Admins.class);

            if(result.isEmpty()){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }

        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }

}
