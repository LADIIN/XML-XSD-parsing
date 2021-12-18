package com.epam.xml.entity.type;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CallPrice", namespace = "http://www.javacourse.by/tariff", propOrder = {"networkCallPrice", "roamingCallPrice"})
public class CallPrice {
    @XmlElement(name = "network-call-price", namespace = "http://www.javacourse.by/tariff", required = true)
    private double networkCallPrice;
    @XmlElement(name = "roaming-call-price", namespace = "http://www.javacourse.by/tariff", required = true)
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
    public String toString() {
        return String.format("CallPrice{NetworkCallPrice = %s, RoamingCallPrice = %s}", networkCallPrice, roamingCallPrice);
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
