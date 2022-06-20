package com.example.storeeverything.user;

import java.util.List;

public class UserListDTO {
    private List<User> users;

    public UserListDTO(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
