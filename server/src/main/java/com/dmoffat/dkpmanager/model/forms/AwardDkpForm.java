package com.dmoffat.dkpmanager.model.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AwardDkpForm {
    @Min(value = 1)
    @NotNull
    private Double amount;

    private String reason;

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
