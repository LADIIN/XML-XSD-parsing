package com.epam.xml.entity.type;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.EnumSet;


@XmlType(name = "tariffication")
@XmlEnum
public enum Tariffication {
    @XmlEnumValue("12 seconds")
    TWELVE_SECONDS("12 seconds"),
    @XmlEnumValue("1 minute")
    MINUTE("1 minute"),
    @XmlEnumValue("1 hour")
    HOUR("1 hour");

    String value;

    public String getValue() {
        return value;
    }

    Tariffication(String value) {
        this.value = value;
    }

    public static Tariffication findByValue(String value) {
        EnumSet<Tariffication> tariffications = EnumSet.allOf(Tariffication.class);

        Tariffication result = null;

        for (Tariffication tariffication : tariffications) {
            if (tariffication.getValue().equals(value)) {
                result = tariffication;
            }
        }

        return result;
    }

}

