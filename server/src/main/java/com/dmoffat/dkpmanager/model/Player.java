package com.dmoffat.dkpmanager.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "guild_id")
    private Guild guild;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private WowClass wowClass;

    @Column(name = "name")
    private String name;

    @Column(name = "dkp")
    private Double dkp;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private List<DkpHistory> dkpHistory;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "forgotten_password_token")
    private String forgottenPasswordToken;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
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
    public List<DkpHistory> getDkpHistory() { return dkpHistory; }
    public void setDkpHistory(List<DkpHistory> dkpHistory) { this.dkpHistory = dkpHistory; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getForgottenPasswordToken() { return forgottenPasswordToken; }
    public void setForgottenPasswordToken(String forgottenPasswordToken) { this.forgottenPasswordToken = forgottenPasswordToken; }
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
                ", email=" + email +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

}
