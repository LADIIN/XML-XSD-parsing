package com.epam.xml.entity.type;

import com.epam.xml.entity.OperatorType;
import com.epam.xml.entity.Parameters;

import java.util.Objects;

public class PhoneTariff extends Tariff {
    private CallPrice callPrice = new CallPrice();
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
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        if (!super.equals(o)){
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
