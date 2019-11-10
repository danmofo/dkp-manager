package com.dmoffat.dkpmanager.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "class")
public class WowClass {

    @Id
    private String id;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @Override
    public String toString() {
        return "WowClass{" +
                "id='" + id + '\'' +
                '}';
    }
}
