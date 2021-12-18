package com.epam.xml.builder.impl;

import com.epam.xml.builder.TariffParser;
import com.epam.xml.entity.Tariff;
import com.epam.xml.entity.Tariffs;
import com.epam.xml.exceptin.TariffException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class TariffJaxbParser implements TariffParser {
    private static final Logger LOGGER = Logger.getLogger(TariffJaxbParser.class.getName());

    public List<Tariff> parse(String xmlPath) throws TariffException {
        List<Tariff> tariffList;

        try {
            JAXBContext context = JAXBContext.newInstance(Tariffs.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            FileReader reader = new FileReader(xmlPath);

            Tariffs tariffs = (Tariffs) unmarshaller.unmarshal(reader);

            tariffList = tariffs.getTariffs();

            LOGGER.log(Level.INFO, "Tariffs have been read and added to list.");
        } catch (JAXBException | FileNotFoundException e) {
            throw new TariffException("Jaxb parsing exception cause:", e);
        }

        return tariffList;
    }
}
