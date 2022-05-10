package api.services;

import database.DatabaseConnection;
import org.springframework.stereotype.Service;

import static src.main.java.model.Tables.GENRES;

@Service
public class GenresSpecificService {

    public void deleteGenre(Integer id) {
        try {
            var create = DatabaseConnection.create();
            create.deleteFrom(GENRES).where(GENRES.GENREID.eq(id)).execute();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
