package com.epam.xml.builder;

import com.epam.xml.entity.type.Tariff;
import com.epam.xml.exceptin.TariffException;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTariffBuilder {
    private List<Tariff> tariffs;

    AbstractTariffBuilder() {
        tariffs = new ArrayList<>();
    }

    abstract public void createTariffs(String xmlPath) throws TariffException;

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }
}
