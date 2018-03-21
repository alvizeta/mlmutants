package com.mercadolibre.mutants.services.analisis;

import com.mercadolibre.mutants.exceptions.InvalidDnaChainException;

public interface DnaAnalisysService {
    boolean analyseAndPersistDna(String [] dna) throws InvalidDnaChainException;
}
