package com.ls.service;

import com.ls.model.User;

public interface IUserService {
    public User findUserByEmail(String email);

    public void saveUser(User user);
}
