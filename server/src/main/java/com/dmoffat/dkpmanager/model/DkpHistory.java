package com.dmoffat.dkpmanager.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dkp_history")
public class DkpHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Player player;

    private Double dkp;

    private LocalDateTime created;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }
    public Double getDkp() { return dkp; }
    public void setDkp(Double dkp) { this.dkp = dkp; }
    public LocalDateTime getCreated() { return created; }
    public void setCreated(LocalDateTime created) { this.created = created; }

    @Override
    public String toString() {
        return "DkpHistory{" +
                "id=" + id +
                ", player=" + player +
                ", dkp=" + dkp +
                ", created=" + created +
                '}';
    }
}
