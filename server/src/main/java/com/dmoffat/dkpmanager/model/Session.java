package com.dmoffat.dkpmanager.model;

import java.util.HashMap;
import java.util.Map;

public class Session {
    private String id;
    private Map<String, Object> data;

    public Session() {
        this.data = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
