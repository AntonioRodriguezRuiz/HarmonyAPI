package connection;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import src.main.java.harmony.Routines;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;

import static src.main.java.harmony.Tables.*;

public class ConnectionTest {
    public static void main(String[] args) {
        Settings settings = new Settings();
        settings.setExecuteLogging(true);

        String USER = "harmony";
        String PASSWORD = "tN1moow41jd6rGfa";
        String URL = "jdbc:mysql://34.65.47.107:3306/harmony";

        // Connection is the only JDBC resource that we need
        // PreparedStatement and ResultSet are handled by jOOQ, internally
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB, settings);
            createExampleEntries(create, conn);

            Result<Record> result = create.select().from(MEDIA).fetch();

            result = create.select().from(PEOPLE).fetch();
            System.out.println("\n");

            // The expected output of this method is the movie Spider-Man, With avg Rating of 3. If it is, it means triggers are working
            result = DSL.using(conn)
                    .select()
                    .from(MEDIA)
                    .join(REVIEWS)
                    .on(MEDIA.MEDIAID.eq(REVIEWS.MEDIAID))
                    .where(MEDIA.MEDIAID.eq(1))
                    .fetch();
            System.out.println("\n");

            // This method serves the purpose of checking if the "on delete" in the tables is working properly
            deleteExampleEntries(conn);

            result = DSL.using(conn)
                    .select()
                    .from(MEDIA)
                    .join(REVIEWS)
                    .on(MEDIA.MEDIAID.eq(REVIEWS.MEDIAID))
                    .where(MEDIA.MEDIAID.eq(1))
                    .fetch();
            System.out.println("\n");

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
            System.out.println("\n");

            Routines.newuser(create.configuration(),
                    "Pedronada"
                    , "pedron@test.com"
                    , "pedron"
                    , (byte) 0);
            System.out.println("\n");

            Integer userid = DSL.using(conn)
                                .select()
                                .from(USERS)
                                .where(USERS.USERNAME.eq("Pedronada"))
                                .fetch().get(0).getValue(USERS.USERID);
            System.out.println("\n");

            Routines.newreview(create.configuration(),
                    userid,
                    1,
                    3.0,
                    "fairly good");
            System.out.println("\n");
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
            System.out.println("\n");

            Integer personid = DSL.using(conn)
                    .select()
                    .from(PEOPLE)
                    .where(PEOPLE.NAME.eq("Pedronada"))
                    .fetch().get(0).getValue(PEOPLE.PERSONID);
            System.out.println("\n");

            DSL.using(conn)
                    .delete(PEOPLE)
                    .where(PEOPLE.PERSONID.eq(personid)).execute();
            System.out.println("\n");

            DSL.using(conn)
                    .delete(USERS)
                    .where(USERS.USERID.eq(userid)).execute();
            System.out.println("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void deleteRemaining(Connection conn) {
        try{
            DSL.using(conn)
                    .delete(REVIEWS)
                    .where(REVIEWS.REVIEW.eq("fairly good")).execute();
            System.out.println("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}