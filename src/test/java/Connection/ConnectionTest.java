package Connection;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.conf.StatementType;
import org.jooq.impl.DSL;
import src.main.java.HarmonyDatabase.Routines;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;

import static src.main.java.HarmonyDatabase.Tables.*;

public class ConnectionTest {
    public static void main(String[] args) {
        Settings settings = new Settings();
        settings.setExecuteLogging(true);

        String userName = "user";
        String password = "user";
        String url = "jdbc:mariadb://localhost:3306/Harmony";

        // Connection is the only JDBC resource that we need
        // PreparedStatement and ResultSet are handled by jOOQ, internally
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB, settings);
            createExampleEntries(create, conn);

            Result<Record> result = create.select().from(MEDIA).fetch();
            for(Record r: result){
                System.out.println(r);
            }

            result = create.select().from(PEOPLE).fetch();
            for(Record r: result){
                System.out.println(r);
            }

            // The expected output of this method is the movie Spider-Man, With avg Rating of 3. If it is, it means triggers are working
            result = DSL.using(conn)
                    .select()
                    .from(MEDIA)
                    .join(REVIEWS)
                    .on(MEDIA.MEDIAID.eq(REVIEWS.MEDIAID))
                    .where(MEDIA.MEDIAID.eq(1))
                    .fetch();
            for(Record r: result){
                System.out.println(r);
            }

            // This method serves the purpose of checking if the "on delete" in the tables is working properly
            deleteExampleEntries(conn);

            result = DSL.using(conn)
                    .select()
                    .from(MEDIA)
                    .join(REVIEWS)
                    .on(MEDIA.MEDIAID.eq(REVIEWS.MEDIAID))
                    .where(MEDIA.MEDIAID.eq(1))
                    .fetch();
            for(Record r: result){
                System.out.println(r);
            }

            // This method is to delete the review on movie with mediaId = 1
            deleteRemaining(conn);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void createExampleEntries(DSLContext create, Connection conn){
        try{
            Routines.newperson(create.configuration(),
                    "Pedronada"
                    , LocalDate.now()
                    , null);

            Routines.newuser(create.configuration(),
                    "Pedronada"
                    , "pedron@test.com"
                    , "pedron"
                    , (byte) 0);

            Integer userid = DSL.using(conn)
                                .select()
                                .from(USERS)
                                .where(USERS.USERNAME.eq("Pedronada"))
                                .fetch().get(0).getValue(USERS.USERID);

            Routines.newreview(create.configuration(),
                    userid,
                    1,
                    3.0,
                    "fairly good");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void deleteExampleEntries(Connection conn){
        try{
            Integer userid = DSL.using(conn)
                    .select()
                    .from(USERS)
                    .where(USERS.USERNAME.eq("Pedronada"))
                    .fetch().get(0).getValue(USERS.USERID);

            Integer personid = DSL.using(conn)
                    .select()
                    .from(PEOPLE)
                    .where(PEOPLE.NAME.eq("Pedronada"))
                    .fetch().get(0).getValue(PEOPLE.PERSONID);

            DSL.using(conn)
                    .delete(PEOPLE)
                    .where(PEOPLE.PERSONID.eq(personid)).execute();

            DSL.using(conn)
                    .delete(USERS)
                    .where(USERS.USERID.eq(userid)).execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void deleteRemaining(Connection conn) {
        try{
            DSL.using(conn)
                    .delete(REVIEWS)
                    .where(REVIEWS.REVIEW.eq("fairly good")).execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}