package com.epam.xml.director;

import com.epam.xml.parser.TariffParser;
import com.epam.xml.entity.Tariff;
import com.epam.xml.exception.TariffException;
import com.epam.xml.factory.ParserType;
import com.epam.xml.factory.TariffParserFactory;
import com.epam.xml.validator.TariffValidator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TariffDirector {
    private static final TariffValidator validator = new TariffValidator();
    private static final Logger LOGGER = Logger.getLogger(TariffDirector.class.getName());

    public List<Tariff> createTariffs(String xmlPath, String xsdPath, ParserType parserType) {
        List<Tariff> tariffs = new ArrayList<>();

        try {
            if (validator.isValid(xmlPath, xsdPath)) {
                TariffParser parser = TariffParserFactory.create(parserType);
                tariffs = parser.parse(xmlPath);
            }
        } catch (TariffException e) {
            LOGGER.log(Level.ERROR, "Creating tariffs error cause: ", e);
        }

        return tariffs;
    }

}
