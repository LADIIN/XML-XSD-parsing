package com.epam.xml.factory;

import com.epam.xml.builder.TariffParser;
import com.epam.xml.builder.impl.TariffDomParser;
import com.epam.xml.builder.impl.TariffJaxbParser;
import com.epam.xml.builder.impl.TariffSaxParser;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class TariffParserFactory {
    private static final Logger LOGGER = Logger.getLogger(TariffParserFactory.class.getName());

    private TariffParserFactory() {
    }

    public static TariffParser create(ParserType type) {

        switch (type) {
            case SAX:
                LOGGER.log(Level.INFO, "Sax parser have been created.");
                return new TariffSaxParser();
            case DOM:
                LOGGER.log(Level.INFO, "Dom parser have been created.");
                return new TariffDomParser();
            case JAXB:
                LOGGER.log(Level.INFO, "JAXB parser have been created.");
                return new TariffJaxbParser();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }

}
