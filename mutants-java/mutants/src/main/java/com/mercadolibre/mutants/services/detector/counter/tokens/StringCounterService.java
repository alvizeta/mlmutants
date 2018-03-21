package com.mercadolibre.mutants.services.detector.counter.tokens;

public interface StringCounterService {
    int countMatchesOnString(String dna,  int count, int minOccurrences, int minMatchs);
}
