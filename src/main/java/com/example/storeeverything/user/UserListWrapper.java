package com.example.storeeverything.user;

import java.util.List;

public class UserListWrapper {
    private List<User> users;

    public UserListWrapper(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
