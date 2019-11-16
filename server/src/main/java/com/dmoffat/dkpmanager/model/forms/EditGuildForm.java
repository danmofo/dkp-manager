package com.dmoffat.dkpmanager.model.forms;

import com.dmoffat.dkpmanager.model.Guild;
import com.dmoffat.dkpmanager.validation.UniqueUri;

import javax.validation.constraints.NotEmpty;

@UniqueUri
public class EditGuildForm {

    private String currentUri;

    @NotEmpty
    private String name;

    @NotEmpty
    private String uri;

    public EditGuildForm() {}

    public EditGuildForm(Guild guild) {
        this.currentUri = guild.getUri();
        this.name = guild.getName();
        this.uri = guild.getUri();
    }

    public String getCurrentUri() { return currentUri; }
    public void setCurrentUri(String currentUri) { this.currentUri = currentUri; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getUri() { return uri; }
    public void setUri(String uri) { this.uri = uri; }
}
