package com.dmoffat.dkpmanager.model.forms;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class SignupForm {

    @NotEmpty
    @Length(min = 4)
    private String characterName;

    @NotEmpty
    @Length(min = 4)
    private String guildName;

    @NotEmpty
    @Length(min = 4)
    private String wowClass;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Length(min = 6)
    private String password;

    public String getCharacterName() { return characterName; }
    public void setCharacterName(String characterName) { this.characterName = characterName; }
    public String getGuildName() { return guildName; }
    public void setGuildName(String guildName) { this.guildName = guildName; }
    public String getWowClass() { return wowClass; }
    public void setWowClass(String wowClass) { this.wowClass = wowClass; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "SignupForm{" +
                "characterName='" + characterName + '\'' +
                ", guildName='" + guildName + '\'' +
                ", wowClass='" + wowClass + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
