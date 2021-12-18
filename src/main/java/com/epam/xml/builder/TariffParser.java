package com.epam.xml.builder;

import com.epam.xml.entity.Tariff;
import com.epam.xml.exceptin.TariffException;

import java.util.List;

public interface TariffParser {
    List<Tariff> parse(String xmlPath) throws TariffException;
}
