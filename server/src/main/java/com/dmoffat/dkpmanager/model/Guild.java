package com.dmoffat.dkpmanager.model;

import com.dmoffat.dkpmanager.model.pagination.Results;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "guild")
public class Guild implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String uri;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private DkpDecayInterval dkpDecayInterval;

    @OneToMany(mappedBy = "guild")
    private List<Player> players;

    @Transient
    private Results<Player> pagedPlayers;

    private String inviteCode;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getUri() { return uri; }
    public void setUri(String uri) { this.uri = uri; }
    public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }
    public LocalDateTime getCreated() { return created; }
    public void setCreated(LocalDateTime created) { this.created = created; }
    public LocalDateTime getUpdated() { return updated; }
    public void setUpdated(LocalDateTime updated) { this.updated = updated; }
    public DkpDecayInterval getDkpDecayInterval() { return dkpDecayInterval; }
    public void setDkpDecayInterval(DkpDecayInterval dkpDecayInterval) { this.dkpDecayInterval = dkpDecayInterval; }
    public String getInviteCode() { return inviteCode; }
    public void setInviteCode(String inviteCode) { this.inviteCode = inviteCode; }
    public Results<Player> getPagedPlayers() { return pagedPlayers; }
    public void setPagedPlayers(Results<Player> pagedPlayers) { this.pagedPlayers = pagedPlayers; }

    @Override
    public String toString() {
        return "Guild{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uri='" + uri + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
