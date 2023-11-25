package com.nttdata.evaluacion.model;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(	name = "phone" )
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long number;
    private int citycode;
    private String countrycode;

    // Getters and setters
}

