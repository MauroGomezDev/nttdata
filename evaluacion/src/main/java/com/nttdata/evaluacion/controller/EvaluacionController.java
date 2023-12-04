package com.nttdata.evaluacion.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nttdata.evaluacion.exceptions.ResponseException;
import com.nttdata.evaluacion.jwt.UserRequest;
import com.nttdata.evaluacion.jwt.UserResponse;
import com.nttdata.evaluacion.model.User;
import com.nttdata.evaluacion.service.UserService;
import com.nttdata.evaluacion.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/evaluacion")
public class EvaluacionController {

    private static final Logger log = LoggerFactory.getLogger(EvaluacionController.class);

    @Autowired
    private UserService userService;

    public EvaluacionController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user-by-email", consumes="application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsrByEmail(@RequestBody UserRequest userRequest) {

        User user = userService.getUserByEmail(userRequest);
        UserResponse userResponse = new UserResponse(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userResponse);
    }

    @PostMapping(value = "/user", consumes="application/json", produces = { "*/*" })
    public ResponseEntity<?> createUsr(@RequestBody UserRequest userRequest) throws JsonProcessingException {

        User newUser = userService.createUser(userRequest);

        UserResponse userResponse = new UserResponse(newUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Utils.mapToJson(userResponse));
    }

    @PutMapping(value = "/update-usr", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUsr(@RequestBody UserRequest updatedUser) {

        UserResponse userResponse = new UserResponse(userService.updateUser(updatedUser));

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userResponse);
    }

    @PatchMapping(value = "/last-login", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateLastLogin(@RequestBody UserRequest updatedUser) {

        UserResponse userResponse = new UserResponse(userService.patchUpdateUser(updatedUser));
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userResponse);
    }

    @DeleteMapping(value = "/delete-usr", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUsr(@RequestBody UserRequest userRequest) {

        userService.deleteUserByEmail(userRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseException("Usuario eliminado correctamente"));

    }

    /**
     * Este metodo solo se creo a modo de prueba
     * @return List
     */
    @GetMapping(value = "/list")
    public List<User> getAllUsers(){
        return userService.listar();
    }
}
