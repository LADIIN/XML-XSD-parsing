package com.epam.xml.builder.impl;

import com.epam.xml.builder.TariffParser;
import com.epam.xml.entity.Tariff;
import com.epam.xml.exceptin.TariffException;
import com.epam.xml.handler.TariffErrorHandler;
import com.epam.xml.handler.TariffHandler;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class TariffSaxParser implements TariffParser {
    private static final Logger LOGGER = Logger.getLogger(TariffSaxParser.class.getName());
    private final TariffHandler handler = new TariffHandler();


    public List<Tariff> parse(String xmlPath) throws TariffException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLReader reader = saxParser.getXMLReader();
            reader.setErrorHandler(new TariffErrorHandler());
            reader.setContentHandler(handler);
            reader.parse(xmlPath);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new TariffException("Sax parsing exception cause: ", e);
        }

        return handler.getTariffs();
    }
}
