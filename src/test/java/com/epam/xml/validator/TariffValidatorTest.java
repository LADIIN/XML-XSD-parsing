package com.epam.xml.validator;

import com.epam.xml.exceptin.TariffException;
import org.junit.Assert;
import org.junit.Test;

public class TariffValidatorTest {

    private static final String VALID_XML_FILE = "src/main/java/resources/data/tariffs.xml";
    private static final String VALID_XSD_FILE = "src/main/java/resources/data/tariffsSchema.xsd";
    private static final String INVALID_XML_FILE = "src/main/java/resources/data/invalidTariffs.xml";
    private static final String EMPTY_PATH = "";

    @Test
    public void testIsValidShouldReturnTrueWhenFileIsValid() throws TariffException {
        //given
        TariffValidator validator = new TariffValidator();

        //when
        boolean actual = validator.isValid(VALID_XML_FILE, VALID_XSD_FILE);

        //then
        Assert.assertTrue(actual);

    }

    @Test
    public void testIsValidShouldReturnFalseWhenFileIsNotValid() throws TariffException {
        //given
        TariffValidator validator = new TariffValidator();

        //when
        boolean actual = validator.isValid(INVALID_XML_FILE, VALID_XSD_FILE);

        //then
        Assert.assertFalse(actual);

    }

    //then
    @Test(expected = TariffException.class)
    public void testIsValidShouldThrowExceptionWhenPathIsEmpty() throws TariffException {
        //given
        TariffValidator validator = new TariffValidator();

        //when
        boolean actual = validator.isValid(EMPTY_PATH, EMPTY_PATH);

    }

    //then
    @Test(expected = TariffException.class)
    public void testIsValidShouldThrowExceptionWhenPathIsNull() throws TariffException {
        //given
        TariffValidator validator = new TariffValidator();

        //when
        boolean actual = validator.isValid(null, null);

    }
}
