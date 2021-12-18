package com.epam.xml.entity;

import com.epam.xml.entity.type.CallPrice;
import com.epam.xml.entity.type.OperatorType;
import com.epam.xml.entity.type.Parameters;

import javax.xml.bind.annotation.*;
import java.util.Objects;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhoneTariff", propOrder = {"callPrice", "smsPrice"})
public class PhoneTariff extends Tariff {
    @XmlElement(name = "call-price", namespace = "http://www.javacourse.by/tariff", required = true)
    private CallPrice callPrice = new CallPrice();
    @XmlElement(name = "sms-price", namespace = "http://www.javacourse.by/tariff", required = true)
    private double smsPrice;

    public PhoneTariff() {
        super();
    }

    public PhoneTariff(String id, String name, OperatorType operator, double payroll,
                       Parameters parameters, boolean isAvailable, CallPrice callPrice, double smsPrice) {
        super(id, name, operator, payroll, parameters, isAvailable);
        this.callPrice = callPrice;
        this.smsPrice = smsPrice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        PhoneTariff that = (PhoneTariff) o;
        return Double.compare(that.smsPrice, smsPrice)
                == 0 && Objects.equals(callPrice, that.callPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), callPrice, smsPrice);
    }

    public CallPrice getCallPrice() {
        return callPrice;
    }

    public void setCallPrice(CallPrice callPrice) {
        this.callPrice = callPrice;
    }

    public double getSmsPrice() {
        return smsPrice;
    }

    public void setSmsPrice(double smsPrice) {
        this.smsPrice = smsPrice;
    }

    @Override
    public String toString() {
        return "PhoneTariff{" + super.toString() +
                "callPrice=" + callPrice +
                ", smsPrice=" + smsPrice +
                '}';
    }


}
