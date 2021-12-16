package com.epam.xml.builder;

import com.epam.xml.entity.type.Tariff;
import com.epam.xml.exceptin.TariffException;
import com.epam.xml.handler.TariffErrorHandler;
import com.epam.xml.handler.TariffHandler;
import com.epam.xml.validator.TariffValidator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class TariffSaxBuilder extends AbstractTariffBuilder {
    private static final Logger LOGGER = Logger.getLogger(TariffSaxBuilder.class.getName());
    private final TariffHandler handler = new TariffHandler();
    private final XMLReader reader;

    public TariffSaxBuilder() throws TariffException {
        super();

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();

            reader = saxParser.getXMLReader();

        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.log(Level.ERROR, "ParserConfigurationException or SAXException.");
            throw new TariffException("ParserConfigurationException or SAXException.");
        }

        reader.setErrorHandler(new TariffErrorHandler());
        reader.setContentHandler(handler);
    }

    @Override
    public void createTariffs(String xmlPath) throws TariffException {

        try {
            reader.parse(xmlPath);

        } catch (IOException | SAXException e) {
            LOGGER.log(Level.ERROR, "IOException or SAXException.");
            throw new TariffException("IOException or SAXException.");

        }
        List<Tariff> tariffs = handler.getTariffs();

        setTariffs(tariffs);
    }
}
