package com.epam.xml.entity.type;

import com.epam.xml.entity.OperatorType;
import com.epam.xml.entity.Parameters;

public abstract class Tariff {
    private String id;
    private String name;
    private OperatorType operator;
    private double payroll;
    private Parameters parameters = new Parameters();
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
        return "Tariff{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", operator=" + operator +
                ", payroll=" + payroll +
                ", parameters=" + parameters +
                ", isAvailable=" + isAvailable +
                '}';
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
