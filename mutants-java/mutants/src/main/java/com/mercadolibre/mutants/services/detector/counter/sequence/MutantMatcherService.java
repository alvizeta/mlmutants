package com.mercadolibre.mutants.services.detector.counter.sequence;

import com.mercadolibre.mutants.services.detector.counter.tokens.StringCounterService;

public interface MutantMatcherService {
    int countMutantPatternMatch(String[] dna, StringCounterService counterService, int count, int minOccurrences, int minLength);
}
