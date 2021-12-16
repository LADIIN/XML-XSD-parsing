package com.epam.xml.validator;

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

    //TODO: Add checking for null-paths

    public boolean isValid(String xmlPath, String xsdPath) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(language);
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

            LOGGER.log(Level.INFO, String.format("File %s is valid.", xmlPath));

        } catch (SAXException | IOException e) {
            LOGGER.log(Level.ERROR, String.format("File %s is not valid.", xmlPath), e);
        }

        return isValid;
    }

}
