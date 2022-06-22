package com.epam.xml.parser;

public enum TariffXmlTag {
    ID("id"),
    AVAILABLE("available"),
    TARIFFS("tariffs"),
    PARAMETERS("parameters"),
    CALL_PRICE("call-price"),
    PHONE_TARIFF("phone-tariff"),
    INTERNET_TARIFF("internet-tariff"),

    NAME("name"),
    OPERATOR("operator"),
    PAYROLL("payroll"),
    TARIFFICATION("tariffication"),
    CONNECTION_PRICE("connection-price"),
    NETWORK_CALL_PRICE("network-call-price"),
    ROAMING_CALL_PRICE("roaming-call-price"),
    SMS_PRICE("sms-price"),
    DOWNLOAD_SPEED("download-speed"),
    UPLOAD_SPEED("upload-speed");

    private final String value;
    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';

    TariffXmlTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TariffXmlTag valueOfXmlTag(String tag) {
        tag = tag.toUpperCase().replace(HYPHEN, UNDERSCORE);

        return TariffXmlTag.valueOf(tag);
    }

}
