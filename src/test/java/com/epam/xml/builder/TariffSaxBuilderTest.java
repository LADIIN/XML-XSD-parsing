package com.epam.xml.builder;

import com.epam.xml.entity.OperatorType;
import com.epam.xml.entity.Parameters;
import com.epam.xml.entity.Tariffication;
import com.epam.xml.entity.type.CallPrice;
import com.epam.xml.entity.type.InternetTariff;
import com.epam.xml.entity.type.PhoneTariff;
import com.epam.xml.entity.type.Tariff;
import com.epam.xml.exceptin.TariffException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TariffSaxBuilderTest {
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

        System.out.println(internetTariff);

        TariffSaxBuilder tariffSaxBuilder = new TariffSaxBuilder();
        List<Tariff> expected = Arrays.asList(phoneTariff, internetTariff);

        //when
        tariffSaxBuilder.createTariffs(VALID_FILE_PATH);

        //then
        List<Tariff> actual = tariffSaxBuilder.getTariffs();

        Assert.assertEquals(expected, actual);


    }

}
