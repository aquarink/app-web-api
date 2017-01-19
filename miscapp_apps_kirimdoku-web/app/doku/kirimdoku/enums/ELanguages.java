package doku.kirimdoku.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ELanguages {
    ID_LANG("ID"),
    EN_LANG("EN"),
    CN_LANG("CN");

    private String eLanguages;
    private static final Map<String, ELanguages> lookup = new HashMap<String, ELanguages>();

    static {
        for (ELanguages s : EnumSet.allOf(ELanguages.class)) {
            getLookup().put(s.code(), s);
        }
    }

    public static Map<String, ELanguages> getLookup() {
        return lookup;
    }

    ELanguages(String eLanguages) {
        this.eLanguages = eLanguages;
    }

    public String code() {
        return eLanguages;
    }
    
}
