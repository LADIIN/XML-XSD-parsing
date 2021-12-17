package com.epam.xml.builder;

import com.epam.xml.entity.InternetTariff;
import com.epam.xml.entity.PhoneTariff;
import com.epam.xml.entity.Tariff;
import com.epam.xml.entity.type.CallPrice;
import com.epam.xml.entity.type.OperatorType;
import com.epam.xml.entity.type.Parameters;
import com.epam.xml.entity.type.Tariffication;
import com.epam.xml.exceptin.TariffException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TariffDomBuilderTest {
    private static final String VALID_FILE_PATH = "src/main/java/resources/data/tariffs.xml";

    @Test
    public void testBuildTariffsShouldCreateWhenFileIsValid() throws TariffException {
        //given
        Parameters phoneParameters = new Parameters(Tariffication.MINUTE, 0.0);
        CallPrice callPrice = new CallPrice(0.75, 1);

        PhoneTariff phoneTariff = new PhoneTariff(
                "tariff001",
                "Super",
                OperatorType.MTS,
                10.25,
                phoneParameters,
                true,
                callPrice,
                0.1
        );

        Parameters internetParameters = new Parameters(Tariffication.HOUR, 2.45);

        InternetTariff internetTariff = new InternetTariff(
                "tariff004",
                "Light",
                OperatorType.MTS,
                11.95,
                internetParameters,
                false,
                30,
                30
        );

        TariffDomBuilder tariffDomBuilder = new TariffDomBuilder();
        List<Tariff> expected = Arrays.asList(phoneTariff, internetTariff);

        //when
        tariffDomBuilder.createTariffs(VALID_FILE_PATH);

        //then
        List<Tariff> actual = tariffDomBuilder.getTariffs();
        Assert.assertEquals(expected, actual);


    }

}

