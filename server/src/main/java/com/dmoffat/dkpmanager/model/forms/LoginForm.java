package com.dmoffat.dkpmanager.model.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class LoginForm {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "LoginForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
