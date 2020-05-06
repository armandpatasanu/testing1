package org.loose.oose.fis.project.exceptions;

public class EmailAlreadyUsedException extends Exception {

    private String email;

    public EmailAlreadyUsedException(String email) {
        super(String.format("An account with the email %s already exists!", email));
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
