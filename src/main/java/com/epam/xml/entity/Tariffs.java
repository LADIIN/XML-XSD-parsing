package com.epam.xml.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "tariffs", namespace = "http://www.javacourse.by/tariff")
public class Tariffs {
    @XmlElements({
            @XmlElement(name = "internet-tariff", namespace = "http://www.javacourse.by/tariff", type = InternetTariff.class),
            @XmlElement(name = "phone-tariff", namespace = "http://www.javacourse.by/tariff", type = PhoneTariff.class)
    })
    private List<Tariff> list = new ArrayList<>();

    public Tariffs() {

    }

    public void setTariffs(List<Tariff> tariffs) {
        this.list = tariffs;
    }

    public boolean add(Tariff tariff) {
        return list.add(tariff);
    }

    public List<Tariff> getTariffs() {
        return list;
    }

}
