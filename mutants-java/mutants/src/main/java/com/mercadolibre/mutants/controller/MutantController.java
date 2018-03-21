package com.mercadolibre.mutants.controller;


import com.mercadolibre.mutants.exceptions.InvalidDnaChainException;
import com.mercadolibre.mutants.model.Dna;
import com.mercadolibre.mutants.model.Stats;
import com.mercadolibre.mutants.services.analisis.DnaAnalisysService;
import com.mercadolibre.mutants.services.stats.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MutantController {

    private final DnaAnalisysService dnaAnalisysService;
    private final StatsService statsService;
    private static ResponseEntity mutantResponse = new ResponseEntity(HttpStatus.OK);
    private static ResponseEntity humanResponse = new ResponseEntity(HttpStatus.FORBIDDEN);

    @Autowired
    public MutantController(DnaAnalisysService dnaAnalisysService, StatsService statsService) {
        this.dnaAnalisysService = dnaAnalisysService;
        this.statsService = statsService;
    }

    @RequestMapping(value = "/mutant", method= RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity isMutant(@RequestBody Dna dna) throws InvalidDnaChainException {

        if (dnaAnalisysService.analyseAndPersistDna(dna.getDna())) {
            return mutantResponse;
        }

        return humanResponse;

    }

    @RequestMapping(value = "/stats", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
     public Stats getStats() {
        return statsService.getStats();
    }

}
