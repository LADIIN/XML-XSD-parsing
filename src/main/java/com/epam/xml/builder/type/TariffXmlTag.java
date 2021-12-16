package com.epam.xml.builder.type;

public enum TariffXmlTag {
    TARIFFS,
    PARAMETERS,
    CALL_PRICE,
    PHONE_TARIFF,
    INTERNET_TARIFF,

    NAME,
    OPERATOR,
    PAYROLL,
    TARIFFICATION,
    CONNECTION_PRICE,
    NETWORK_CALL_PRICE,
    ROAMING_CALL_PRICE,
    SMS_PRICE,
    DOWNLOAD_SPEED,
    UPLOAD_SPEED;

    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';

    @Override
    public String toString() {
        String name = this.name();

        return name.toLowerCase().replace(UNDERSCORE, HYPHEN);
    }

    public static TariffXmlTag valueOfXmlTag(String tag) {
        tag = tag.toUpperCase().replace(HYPHEN, UNDERSCORE);

        return TariffXmlTag.valueOf(tag);
    }

}
