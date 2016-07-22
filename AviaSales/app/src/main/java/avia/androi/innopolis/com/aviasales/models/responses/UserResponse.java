package avia.androi.innopolis.com.aviasales.models.responses;

import avia.androi.innopolis.com.aviasales.models.User;

public class UserResponse {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String message;
    private int code;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
