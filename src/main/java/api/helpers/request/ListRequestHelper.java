package api.helpers.request;

import api.helpers.response.ListResponseHelper;

/**
 * ListRequestHelper
 * Project HarmonyAPI
 * Created: 2022-05-06
 *
 * @author juagallop1
 **/
public record ListRequestHelper(String listName, String icon) {
    public ListRequestHelper(ListResponseHelper oldlist, ListRequestHelper list) {
        this(
            list.listName() == null ? oldlist.listName() : list.listName(),
            list.icon() == null ? oldlist.icon() : list.icon()
        );
    }
}
