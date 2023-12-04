package com.nttdata.evaluacion.service;

import com.nttdata.evaluacion.jwt.UserRequest;
import com.nttdata.evaluacion.model.User;

import java.util.List;

public interface UserService {

    User createUser(UserRequest userRequest);
    User getUserByEmail(UserRequest userRequest);
    User updateUser(UserRequest userRequest);
    User patchUpdateUser(UserRequest userRequest);
    boolean deleteUserByEmail(UserRequest userRequest);
    String createJwtToken(User user);
    List<User> listar();


}
