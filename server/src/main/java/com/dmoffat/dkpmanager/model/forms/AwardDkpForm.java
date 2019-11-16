package com.dmoffat.dkpmanager.model.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AwardDkpForm {
    @Min(value = 1)
    @NotNull
    private Double amount;

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}
