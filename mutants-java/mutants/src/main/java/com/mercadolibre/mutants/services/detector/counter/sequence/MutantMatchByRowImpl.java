package com.mercadolibre.mutants.services.detector.counter.sequence;

import com.mercadolibre.mutants.services.detector.counter.tokens.StringCounterService;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class MutantMatchByRowImpl implements MutantMatcherService {
    @Override
    public int countMutantPatternMatch(String[] dna, StringCounterService stringCounterService, int count, int minOccurrences, int minLength) {
        return Stream.of(dna).parallel()
                .mapToInt(e -> stringCounterService.countMatchesOnString(e,count,minOccurrences,minLength)).sum();
    }
}
