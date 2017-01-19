package models;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ColExcel {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7),
    I(8),
    J(9),
    K(10),
    L(11),
    M(12),
    N(13),
    O(14)
    ;

    /**
     * @return the lookupString
     */
    public static Map<String, ColExcel> getLookupValue() {
        return lookupValue;
    }

    int status;
    private static final Map<String, ColExcel> lookup = new HashMap<String, ColExcel>();
    private static Map<String, ColExcel> lookupValue = new HashMap<String, ColExcel>();

    static {
        for (ColExcel s : EnumSet.allOf(ColExcel.class)) {
            getLookup().put(s.name(), s);
            getLookupValue().put(s.toString(), s);
        }
    }

    public static Map<String, ColExcel> getLookup() {
        return lookup;
    }

    ColExcel(int c) {
        status = c;
    }

    public int value() {
        return status;
    }
    
    public int column() {
    	return status+1;
    }
}


