package com.epam.xml.entity;

public class Parameters {
    private Tariffication tariffication;
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

    public void setTariffication(Tariffication tariffication) {
        this.tariffication = tariffication;
    }

    public void setConnectionPrice(double connectionPrice) {
        this.connectionPrice = connectionPrice;
    }
}
