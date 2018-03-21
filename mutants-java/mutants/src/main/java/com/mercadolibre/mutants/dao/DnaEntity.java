package com.mercadolibre.mutants.dao;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class DnaEntity {
    @Id
    private Long dnaHashCode;

    @Column(columnDefinition = "TEXT")
    private String Dna;

    private boolean isMutant;

    private LocalDateTime dateTime  = LocalDateTime.now();

}
