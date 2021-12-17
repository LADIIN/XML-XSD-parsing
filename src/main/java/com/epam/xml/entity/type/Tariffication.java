package com.epam.xml.entity.type;


import java.util.EnumSet;

public enum Tariffication {
    TWELVE_SECONDS,
    MINUTE,
    HOUR;

    @Override
    public String toString() {
        String tariffication;

        switch (this) {
            case TWELVE_SECONDS:
                tariffication = "12 seconds";
                break;
            case MINUTE:
                tariffication = "1 minute";
                break;
            case HOUR:
                tariffication = "1 hour";
                break;
            default:
                tariffication = "Unresolved tariffication";
        }

        return tariffication;
    }

    public static Tariffication findByValue(String value) {
        EnumSet<Tariffication> tariffications = EnumSet.allOf(Tariffication.class);

        Tariffication result = null;

        for (Tariffication tariffication : tariffications) {
            if (tariffication.toString().equals(value)) {
                result = tariffication;
            }
        }

        return result;
    }

}
