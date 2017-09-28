package dev.roysez.financemanager.service;

import dev.roysez.financemanager.model.User;

import java.io.IOException;

public interface UserService {

    User getUser() throws IOException;

    User saveUser(User user) throws IOException;


}
