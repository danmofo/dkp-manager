package com.dmoffat.dkpmanager.model.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DecayDkpForm {
    @Min(value = 1)
    @NotNull
    private Double amount;

    @NotNull
    private Integer playerId;

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public Integer getPlayerId() { return playerId; }
    public void setPlayerId(Integer playerId) { this.playerId = playerId; }
}
