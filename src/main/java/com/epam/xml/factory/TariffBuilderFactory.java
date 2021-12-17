package com.epam.xml.factory;

import com.epam.xml.builder.AbstractTariffBuilder;
import com.epam.xml.builder.TariffDomBuilder;
import com.epam.xml.builder.TariffSaxBuilder;
import com.epam.xml.entity.Tariff;
import com.epam.xml.exceptin.TariffException;
import org.apache.log4j.Logger;

public class TariffBuilderFactory {
    private static final Logger LOGGER = Logger.getLogger(TariffBuilderFactory.class.getName());

    private TariffBuilderFactory() {
    }


    public static AbstractTariffBuilder newBuilder(ParserType type) throws TariffException {
        AbstractTariffBuilder builder;

        switch (type) {
            case SAX:
                builder = new TariffSaxBuilder();
                break;
            case DOM:
                builder = new TariffDomBuilder();
                break;
            default:
                throw new EnumConstantNotPresentException(
                        type.getDeclaringClass(), type.name());
        }
        return builder;
    }

}
