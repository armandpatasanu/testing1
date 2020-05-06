package org.loose.oose.fis.project.exceptions;

public class UserCredentialsInvalidException extends Exception {

    private String username,password;

    public UserCredentialsInvalidException(String username,String password) {
        super(String.format("The username or password are incorrect!"));
    }
    public String getUsername() { return username;
    }
    public String getPassword() {
        return password;
    }

}
