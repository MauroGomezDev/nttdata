package com.nttdata.evaluacion.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nttdata.evaluacion.exceptions.ResponseException;
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
//@CrossOrigin("*")
//@CrossOrigin(origins = "http://localhost:8080")
public class EvaluacionController {

    private static final Logger log = LoggerFactory.getLogger(EvaluacionController.class);

    @Autowired
    private UserService userService;

    public EvaluacionController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/get-usr-by-email", consumes="application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsrByEmail(@RequestBody String email) {

        User user = userService.getUserByEmail(email);
        UserResponse userResponse = new UserResponse(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userResponse);
    }

    @PostMapping(value = "/create-usr", consumes="application/json", produces = { "*/*" })
    public ResponseEntity<?> createUsr(@RequestBody User user) throws JsonProcessingException {
        log.info("Procesando "+user);
        User newUser = userService.createUser(user);
        log.info("Usuario Creado OK "+newUser);
        UserResponse userResponse = new UserResponse(newUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Utils.mapToJson(userResponse));
    }

    @PutMapping(value = "/update-usr/{email}", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUsr(@PathVariable String email, @RequestBody User updatedUser) {

        UserResponse userResponse = new UserResponse(userService.updateUser(email, updatedUser));

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userResponse);
    }

    @PatchMapping(value = "/update-last-login", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateLastLogin(@RequestBody String email) {

        UserResponse userResponse = new UserResponse(userService.patchUpdateUser(email));
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userResponse);
    }

    @DeleteMapping(value = "/delete-usr/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUsr(@PathVariable String email) {

        userService.deleteUserByEmail(email);
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
