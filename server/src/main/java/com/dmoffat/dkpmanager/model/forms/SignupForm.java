package com.dmoffat.dkpmanager.model.forms;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SignupForm {

    @NotEmpty
    @Length(min = 4)
    private String characterName;

    @NotNull
    private Integer guildId;

    @NotNull
    private String wowClassId;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Length(min = 6)
    private String password;

    public String getCharacterName() { return characterName; }
    public void setCharacterName(String characterName) { this.characterName = characterName; }
    public Integer getGuildId() { return guildId; }
    public void setGuildId(Integer guildId) { this.guildId = guildId; }
    public String getWowClassId() { return wowClassId; }
    public void setWowClassId(String wowClassId) { this.wowClassId = wowClassId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "SignupForm{" +
                "characterName='" + characterName + '\'' +
                ", guildId='" + guildId + '\'' +
                ", wowClassId='" + wowClassId + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
