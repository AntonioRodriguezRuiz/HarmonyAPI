package api.helpers.enums;

/**
 * TrackerState
 * Project HarmonyAPI
 * Created: 2022-05-05
 *
 * @author juagallop1
 **/
public enum TrackerState {
    PLANNING,
    IN_PROGRESS,
    COMPLETED,
    DID_NOT_FINISH,
    ABANDONED;

    public static TrackerState of(Integer state) {
        return switch (state) {
            case 0 -> PLANNING;
            case 1 -> IN_PROGRESS;
            case 2 -> COMPLETED;
            case 3 -> DID_NOT_FINISH;
            case 4 -> ABANDONED;
            default -> null;
        };
    }
}
