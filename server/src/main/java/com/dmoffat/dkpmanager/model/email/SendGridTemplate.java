package com.dmoffat.dkpmanager.model.email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SendGridTemplate {
    private String id;
    private String name;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "SendGridTemplate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
