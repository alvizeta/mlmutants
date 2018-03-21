package com.mercadolibre.mutants.services.detector;

import com.mercadolibre.mutants.exceptions.InvalidDnaChainException;

public interface MutantDetector {
    boolean isMutant(String[] dna) throws InvalidDnaChainException;
}
