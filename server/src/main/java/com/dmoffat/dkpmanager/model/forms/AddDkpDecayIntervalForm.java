package com.dmoffat.dkpmanager.model.forms;

import com.dmoffat.dkpmanager.model.UnitName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AddDkpDecayIntervalForm {

    @NotNull
    private UnitName unitName;

    @Min(1)
    @NotNull
    private Integer unitValue;

    @Min(1)
    @NotNull
    private Double dkp;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    public UnitName getUnitName() { return unitName; }
    public void setUnitName(UnitName unitName) { this.unitName = unitName; }
    public Integer getUnitValue() { return unitValue; }
    public void setUnitValue(Integer unitValue) { this.unitValue = unitValue; }
    public Double getDkp() { return dkp; }
    public void setDkp(Double dkp) { this.dkp = dkp; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    @Override
    public String toString() {
        return "AddDkpDecayIntervalForm{" +
                "dkp=" + dkp +
                "unitName=" + unitName +
                ", unitValue=" + unitValue +
                ", startDate=" + startDate +
                '}';
    }
}
