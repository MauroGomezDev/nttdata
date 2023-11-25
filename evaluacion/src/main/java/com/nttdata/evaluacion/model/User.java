package com.nttdata.evaluacion.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(	name = "apiuser" )
public class User implements Comparable<User>{
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Phone> phones;

    private LocalDate created;
    private LocalDate modified;
    private LocalDate lastLogin;
    private String token;
    private boolean isActive;

    public User(User user) {
    }

    /**
     * Se crea este metodo para poder ordenar la lista con el stream
     * @param otherUser
     * @return
     */
    @Override
    public int compareTo(User otherUser) {
        return this.name.compareTo(otherUser.getName());
    }

}

