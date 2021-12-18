package com.epam.xml.handler;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class TariffErrorHandler extends DefaultHandler {
    private static final Logger LOGGER = Logger.getLogger(TariffErrorHandler.class.getName());
    private boolean isError = false;

    public void warning(SAXParseException e) {
        LOGGER.log(Level.WARN, String.format("Warning: [%s] - %s", getLineAddress(e), e.getMessage()));
        isError = true;
    }

    public void error(SAXParseException e) {
        LOGGER.log(Level.WARN, String.format("Error: [%s] - %s", getLineAddress(e), e.getMessage()));
        isError = true;

    }

    public void fatalError(SAXParseException e) {
        LOGGER.log(Level.FATAL, String.format("Fatal error: [%s] - %s", getLineAddress(e), e.getMessage()));
        isError = true;
    }

    private String getLineAddress(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }

    public boolean isErrorHappened() {
        return isError;
    }

}
