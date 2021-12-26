package com.epam.xml.entity;

import com.epam.xml.entity.type.OperatorType;
import com.epam.xml.entity.type.Parameters;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tariff", propOrder = {"name", "operator", "payroll", "parameters"})
@XmlSeeAlso({PhoneTariff.class, InternetTariff.class})
public abstract class Tariff {
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    private String id;
    @XmlElement(namespace = "http://www.javacourse.by/tariff")
    private String name;
    @XmlElement(namespace = "http://www.javacourse.by/tariff")
    private OperatorType operator;
    @XmlElement(namespace = "http://www.javacourse.by/tariff")
    private double payroll;
    @XmlElement(namespace = "http://www.javacourse.by/tariff")
    private Parameters parameters = new Parameters();
    @XmlAttribute(name = "available")
    private boolean isAvailable;

    public Tariff() {

    }

    public Tariff(String id, String name, OperatorType operator,
                  double payroll, Parameters parameters, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.operator = operator;
        this.payroll = payroll;
        this.parameters = parameters;
        this.isAvailable = isAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tariff tariff = (Tariff) o;

        return Double.compare(payroll, tariff.getPayroll()) == 0
                && isAvailable == tariff.isAvailable()
                && id.equals(tariff.getId())
                && name.equals(tariff.getName())
                && operator == tariff.getOperator()
                && parameters.equals(tariff.getParameters());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        long bits = Double.doubleToLongBits(payroll);
        result = prime * result + (int) (bits ^ (bits >>> 32));
        result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return String.format("\nID = %s\nName = %s\nOperator = %s\nPayroll = %s\nParameters = %s \nIsAvailable = %s\n",
                id, name, operator, payroll, parameters, isAvailable);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public OperatorType getOperator() {
        return operator;
    }

    public double getPayroll() {
        return payroll;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOperator(OperatorType operator) {
        this.operator = operator;
    }

    public void setPayroll(double payroll) {
        this.payroll = payroll;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
