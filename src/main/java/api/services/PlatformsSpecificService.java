package api.services;

import database.DatabaseConnection;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

import static src.main.java.model.Tables.PLATFORMS;

@Service
public class PlatformsSpecificService {

    public void deletePlatform(Integer id) throws SQLException {
        try {
            var create = DatabaseConnection.create();

            create.deleteFrom(PLATFORMS)
                    .where(PLATFORMS.PLATFORMID.eq(id))
                    .execute();

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

}
