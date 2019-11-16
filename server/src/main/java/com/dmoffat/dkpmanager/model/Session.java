package com.dmoffat.dkpmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class Session {
    private String id;
    private Map<String, Object> data;
    @JsonIgnore private boolean changed = false;
    @JsonIgnore private Player player;

    public Session() {
        this.data = new HashMap<>();
    }

    @JsonIgnore
    public Integer getPlayerId() {
        Object playerId = data.get("playerId");
        if(playerId == null) {
            return null;
        }
        return (Integer)playerId;
    }

    @JsonIgnore
    public void setPlayer(Player player) {
        this.player = player;
    }

    @JsonIgnore
    public Player getPlayer() {
        return player;
    }

    @JsonIgnore
    public boolean isLoggedIn() {
        return this.data.get("playerId") != null;
    }

    @JsonIgnore
    public String getMessage() {
        System.out.println("Getting message");
        String message = (String)this.data.get("message");
        removeData("message");
        return message;
    }

    public Session addData(String key, Object value) {
        Object existingValue = this.data.get(key);
        if(existingValue == null || !existingValue.equals(value)) {
            this.data.put(key, value);
            this.changed = true;
        }
        return this;
    }

    public Session removeData(String key) {
        System.out.println("Removing key: " + key);
        Object removedValue = this.data.remove(key);
        if(removedValue != null) {
            this.changed = true;
        }
        return this;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Map<String, Object> getData() { return data; }
    public void setData(Map<String, Object> data) { this.data = data; }
    public boolean isChanged() { return changed; }
    public void setChanged(boolean changed) { this.changed = changed; }

    @Override
    public String toString() {
        return "Session{" +
                "id='" + id + '\'' +
                ", data=" + data +
                ", changed=" + changed +
                '}';
    }
}
