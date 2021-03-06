package populator;

import info.movito.themoviedbapi.TmdbApi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import static com.harmonyapi.Application.dotenv;

/**
 * GlobalValues
 * Project HarmonyAPI
 * Created: 2022-05-05
 *
 * @author juagallop1
 **/
public class Global {
    public static final TmdbApi TMDB = new TmdbApi(dotenv.get("HARMONY_TMDB_API_KEY"));
    public static final String TMDB_IMAGE_URL = "https://www.themoviedb.org/t/p/original";

    public static void gunzip(String fp) {
        byte[] buffer = new byte[1024];
        try (
            GZIPInputStream gzip = new GZIPInputStream(new FileInputStream(fp));
            FileOutputStream out = new FileOutputStream(fp.replace(".gz", ""))
        ) {
            int len;
            while ((len = gzip.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
