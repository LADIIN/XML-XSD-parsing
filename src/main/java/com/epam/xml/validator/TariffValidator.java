package com.epam.xml.validator;

import com.epam.xml.exceptin.TariffException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import com.epam.xml.handler.TariffErrorHandler;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class TariffValidator {

    private static final Logger LOGGER = Logger.getLogger(TariffValidator.class.getName());

    public boolean isValid(String xmlPath, String xsdPath) throws TariffException {
        if (xmlPath == null || xmlPath.isEmpty() || xsdPath == null || xsdPath.isEmpty()) {
            throw new TariffException("XML or XSD filepath is empty or null.");
        }

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        boolean isValid = true;

        try {
            File xsdFile = new File(xsdPath);

            Schema schema = schemaFactory.newSchema(xsdFile);
            Source source = new StreamSource(xmlPath);

            Validator validator = schema.newValidator();
            TariffErrorHandler errorHandler = new TariffErrorHandler();
            validator.setErrorHandler(errorHandler);

            validator.validate(source);

            if (errorHandler.isErrorHappened()) {
                isValid = false;
            }
        } catch (SAXException | IOException e) {
            LOGGER.log(Level.ERROR, String.format("File %s is not valid.", xmlPath), e);
        }

        LOGGER.log(Level.INFO, String.format("File %s is valid - %b", xmlPath, isValid));

        return isValid;
    }

}
