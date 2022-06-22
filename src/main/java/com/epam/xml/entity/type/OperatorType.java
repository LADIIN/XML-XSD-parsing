package com.epam.xml.entity.type;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "operator")
@XmlEnum
public enum OperatorType {
    @XmlEnumValue("MTS")
    MTS,
    @XmlEnumValue("A1")
    A1,
    @XmlEnumValue("LIFE")
    LIFE
}
