package com.kondie.pm_admin;

/**
 * Created by kondie on 2018/02/17.
 */

public class MechanicItem {

    String mechanicEmail, mechanicName, mechanicDpPath, mechanicStatus, lastUpdated, mechanicPhone;

    public MechanicItem(){}

    public MechanicItem(String mechanicEmail, String mechanicName, String mechanicDpPath, String mechanicStatus, String lastUpdated, String mechanichone){

        this.mechanicEmail = mechanicEmail;
        this.mechanicName = mechanicName;
        this.mechanicDpPath = mechanicDpPath;
        this.mechanicStatus = mechanicStatus;
        this.lastUpdated = lastUpdated;
    }

    public void setMechanicPhone(String mechanicPhone) {
        this.mechanicPhone = mechanicPhone;
    }

    public String getMechanicPhone() {
        return mechanicPhone;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setMechanicStatus(String mechanicStatus) {
        this.mechanicStatus = mechanicStatus;
    }

    public String getMechanicStatus() {
        return mechanicStatus;
    }

    public void setMechanicDpPath(String mechanicDpPath) {
        this.mechanicDpPath = mechanicDpPath;
    }

    public void setMechanicEmail(String mechanicEmail) {
        this.mechanicEmail = mechanicEmail;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }

    public String getMechanicDpPath() {
        return mechanicDpPath;
    }

    public String getMechanicEmail() {
        return mechanicEmail;
    }

    public String getMechanicName() {
        return mechanicName;
    }
}

