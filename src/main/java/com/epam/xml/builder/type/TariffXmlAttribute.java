package com.epam.xml.builder.type;


public enum TariffXmlAttribute {
    ID,
    AVAILABLE;

    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';

    @Override
    public String toString() {
        return this.name().toLowerCase().replace(UNDERSCORE, HYPHEN);
    }

    public static TariffXmlTag valueOfXmlTag(String tag) {
        tag = tag.toUpperCase().replace(HYPHEN, UNDERSCORE);

        return TariffXmlTag.valueOf(tag);
    }
}
