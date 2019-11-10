package com.dmoffat.dkpmanager.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Guild guild;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private WowClass wowClass;

    private String name;
    private Double dkp;
    private LocalDateTime created;
    private LocalDateTime updated;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Guild getGuild() { return guild; }
    public void setGuild(Guild guild) { this.guild = guild; }
    public WowClass getWowClass() { return wowClass; }
    public void setWowClass(WowClass wowClass) { this.wowClass = wowClass; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getDkp() { return dkp; }
    public void setDkp(Double dkp) { this.dkp = dkp; }
    public LocalDateTime getCreated() { return created; }
    public void setCreated(LocalDateTime created) { this.created = created; }
    public LocalDateTime getUpdated() { return updated; }
    public void setUpdated(LocalDateTime updated) { this.updated = updated; }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dkp=" + dkp +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

}
