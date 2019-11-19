package com.dmoffat.dkpmanager.model.forms;

import com.dmoffat.dkpmanager.model.UnitName;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AddDkpDecayIntervalForm {

    @NotNull
    private UnitName unitName;

    @Min(1)
    @NotNull
    private Integer unitValue;

    @Min(1)
    @NotNull
    private Double dkp;

    public UnitName getUnitName() { return unitName; }
    public void setUnitName(UnitName unitName) { this.unitName = unitName; }
    public Integer getUnitValue() { return unitValue; }
    public void setUnitValue(Integer unitValue) { this.unitValue = unitValue; }
    public Double getDkp() { return dkp; }
    public void setDkp(Double dkp) { this.dkp = dkp; }

    @Override
    public String toString() {
        return "AddDkpDecayIntervalForm{" +
                "dkp=" + dkp +
                "unitName=" + unitName +
                ", unitValue=" + unitValue +
                '}';
    }
}
