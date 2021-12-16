package com.epam.xml.entity.type;

import java.util.Objects;

public class CallPrice {
    private double networkCallPrice;
    private double roamingCallPrice;

    public CallPrice(double networkCallPrice, double roamingCallPrice) {
        this.networkCallPrice = networkCallPrice;
        this.roamingCallPrice = roamingCallPrice;
    }

    public CallPrice() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CallPrice callPrice = (CallPrice) o;
        return Double.compare(callPrice.networkCallPrice, networkCallPrice) == 0
                && Double.compare(callPrice.roamingCallPrice, roamingCallPrice) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(networkCallPrice, roamingCallPrice);
    }

    public double getNetworkCallPrice() {
        return networkCallPrice;
    }

    public void setNetworkCallPrice(double networkCallPrice) {
        this.networkCallPrice = networkCallPrice;
    }

    public double getRoamingCallPrice() {
        return roamingCallPrice;
    }

    public void setRoamingCallPrice(double roamingCallPrice) {
        this.roamingCallPrice = roamingCallPrice;
    }
}
