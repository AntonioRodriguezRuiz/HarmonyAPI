package api.helpers.request;

import api.helpers.enums.TrackerState;

/**
 * TrackerRequestHelper
 * Project HarmonyAPI
 * Created: 2022-05-06
 *
 * @author juagallop1
 **/
public record TrackerRequestHelper(Integer mediaId, TrackerState state) {
}
