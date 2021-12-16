package com.epam.xml.exceptin;

public class TariffException extends Exception {
    public TariffException() {
        super();
    }

    public TariffException(String message) {
        super(message);
    }

    public TariffException(Exception e) {
        super(e);
    }

    public TariffException(String message, Exception cause) {
        super(message, cause);
    }
}
