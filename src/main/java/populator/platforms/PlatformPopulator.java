package populator.platforms;

import api.helpers.request.PlatformRequestHelper;
import api.helpers.response.MediaResponseHelper;
import api.services.MediaSpecificService;
import api.services.PlatformsService;
import src.main.java.model.tables.pojos.Platforms;

import java.sql.SQLException;
import java.util.List;

/**
 * PlatformPopulator
 * Project HarmonyAPI
 * Created: 2022-05-11
 *
 * @author juagallop1
 **/
public class PlatformPopulator {
    private static PlatformsService platformsService = new PlatformsService();
    private static MediaSpecificService mediaSpecificService = new MediaSpecificService();
    private static List<Platforms> allPlatforms;

    private static Integer addPlatform(String platformName) throws SQLException {
        var platform = allPlatforms.stream()
            .filter(g -> g.getPlatformname().equals(platformName.toLowerCase()))
            .findFirst();
        if (platform.isPresent()) {
            return platform.get().getPlatformid();
        } else {
            return platformsService.postPlatform(
                new PlatformRequestHelper(null, null, platformName.toLowerCase())
            ).getPlatformid();
        }
    }

    public static void addPlatformsVideogames(List<String> platforms, MediaResponseHelper dbVideogame) {
        allPlatforms = platformsService.getAllPlatforms();
        platforms.stream()
            .map(platform -> {
                try {
                    return addPlatform(platform);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            })
            .forEach(platformId -> {
                    try {
                        mediaSpecificService.postPlatform(dbVideogame.getMediaid(), new PlatformRequestHelper(null, platformId, null));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            );
    }
}
