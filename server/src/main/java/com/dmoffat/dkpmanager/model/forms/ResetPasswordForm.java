package com.dmoffat.dkpmanager.model.forms;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;

public class ResetPasswordForm {
    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @AssertTrue
    public boolean isPasswordsMatch() {
        System.out.println("doPasswordsMatch?");
        if((password == null || password.isEmpty()) || (confirmPassword == null || confirmPassword.isEmpty())) {
            System.out.println("One of the required fields is missing.");
            return true;
        }
        System.out.println("Both fields are entered, doing the comparison.");
        return password.equals(confirmPassword);
    }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
}
