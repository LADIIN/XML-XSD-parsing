package com.epam.xml.entity.type;

import com.epam.xml.entity.OperatorType;
import com.epam.xml.entity.Parameters;

import java.util.Objects;

public class InternetTariff extends Tariff {
    private  double downloadSpeed;
    private  double uploadSpeed;

    public InternetTariff(){
        super();
    }

    public InternetTariff(String id, String name, OperatorType operator, double payroll,
                          Parameters parameters, boolean isAvailable, double downloadSpeed, double uploadSpeed) {
        super(id, name, operator, payroll, parameters, isAvailable);
        this.downloadSpeed = downloadSpeed;
        this.uploadSpeed = uploadSpeed;
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

        InternetTariff that = (InternetTariff) o;
        return Double.compare(that.downloadSpeed, downloadSpeed) == 0
                && Double.compare(that.uploadSpeed, uploadSpeed) == 0;
    }

    @Override
    public String toString() {
        return "InternetTariff{" + super.toString() +
                "downloadSpeed=" + downloadSpeed +
                ", uploadSpeed=" + uploadSpeed +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), downloadSpeed, uploadSpeed);
    }

    public double getDownloadSpeed() {
        return downloadSpeed;
    }

    public double getUploadSpeed() {
        return uploadSpeed;
    }

    public void setDownloadSpeed(double downloadSpeed) {
        this.downloadSpeed = downloadSpeed;
    }

    public void setUploadSpeed(double uploadSpeed) {
        this.uploadSpeed = uploadSpeed;
    }
}

