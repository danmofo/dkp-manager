package com.dmoffat.dkpmanager.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "dkp_decay_interval")
public class DkpDecayInterval {

    @Id
    @Column(name = "guild_id")
    private Integer guildId;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit_name")
    private UnitName unitName;

    @Column(name = "unit_value")
    private Integer unitValue;

    @Column(name = "dkp")
    private Double dkp;

    @Column(name = "next_occurrence")
    private LocalDate nextOccurrence;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    public UnitName getUnitName() { return unitName; }
    public void setUnitName(UnitName unitName) { this.unitName = unitName; }
    public Integer getUnitValue() { return unitValue; }
    public void setUnitValue(Integer unitValue) { this.unitValue = unitValue; }
    public LocalDateTime getCreated() { return created; }
    public void setCreated(LocalDateTime created) { this.created = created; }
    public LocalDateTime getUpdated() { return updated; }
    public void setUpdated(LocalDateTime updated) { this.updated = updated; }
    public Integer getGuildId() { return guildId; }
    public void setGuildId(Integer guildId) { this.guildId = guildId; }
    public Double getDkp() { return dkp; }
    public void setDkp(Double dkp) { this.dkp = dkp; }
    public LocalDate getNextOccurrence() { return nextOccurrence; }
    public void setNextOccurrence(LocalDate nextOccurrence) { this.nextOccurrence = nextOccurrence; }

    @Override
    public String toString() {
        return "DkpDecayInterval{" +
                ", guildId=" + guildId +
                ", dkp=" + dkp +
                ", nextOccurrence=" + nextOccurrence +
                ", unitName=" + unitName +
                ", unitValue=" + unitValue +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
