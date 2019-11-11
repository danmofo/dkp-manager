package com.dmoffat.dkpmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class Session {
    private String id;
    private Map<String, Object> data;
    @JsonIgnore private boolean changed = false;

    public Session() {
        this.data = new HashMap<>();
    }

    @JsonIgnore
    public boolean isLoggedIn() {
        return this.data.get("playerId") != null;
    }

    public Session addData(String key, Object value) {
        Object existingValue = this.data.get(key);
        if(existingValue == null || !existingValue.equals(value)) {
            this.data.put(key, value);
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
}
