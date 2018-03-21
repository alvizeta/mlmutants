package com.mercadolibre.mutants.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
public class Stats {

    @JsonProperty("count_mutant_dna")
    private long countedMutant;
    @JsonProperty("count_human_dna")
    private long countedHumans;
    @JsonProperty("ratio")
    private double ratio = 0;

    public void incrementCountedHumans() {
        countedHumans++;
        ratio = (double)countedMutant/countedHumans;
    }

    public void incrementCountedMutants() {
        countedMutant++;
        ratio = countedHumans == 0 ? countedMutant : (double)countedMutant/countedHumans;
    }

}
