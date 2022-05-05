package api.helpers.enums;

import org.jooq.TableField;

public enum RoleType {
    CAST, CREW;

    public static RoleType of(Byte roletype) {
        switch (roletype.intValue()){
            case 0: return CREW;
            case 1: return CAST;
        }
        return null;
    }
}
