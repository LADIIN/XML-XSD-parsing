package com.epam.xml.entity.type;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Parameters", propOrder = {"tariffication", "connectionPrice"})
public class Parameters {
    @XmlElement(name = "tariffication", namespace = "http://www.javacourse.by/tariff", required = true)
    private Tariffication tariffication;
    @XmlElement(name = "connection-price", namespace = "http://www.javacourse.by/tariff", required = true)
    private double connectionPrice;

    public Parameters() {

    }

    public Parameters(Tariffication tariffication, double connectionPrice) {
        this.tariffication = tariffication;
        this.connectionPrice = connectionPrice;
    }

    public Tariffication getTariffication() {
        return tariffication;
    }

    public double getConnectionPrice() {
        return connectionPrice;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Parameters parameters = (Parameters) object;

        return tariffication == parameters.getTariffication()
                && Double.compare(connectionPrice, parameters.getConnectionPrice()) == 0;

    }

    @Override
    public int hashCode() {
        int hashCode = tariffication.hashCode();

        long bits = Double.doubleToLongBits(connectionPrice);

        hashCode = 31 * hashCode + (int) (bits ^ (bits >>> 32));

        return hashCode;
    }

    @Override
    public String toString() {
        return String.format("Parameters{ tariffication = %s , connectionPrice = %s }", tariffication, connectionPrice);
    }

    public void setTariffication(Tariffication tariffication) {
        this.tariffication = tariffication;
    }

    public void setConnectionPrice(double connectionPrice) {
        this.connectionPrice = connectionPrice;
    }
}
