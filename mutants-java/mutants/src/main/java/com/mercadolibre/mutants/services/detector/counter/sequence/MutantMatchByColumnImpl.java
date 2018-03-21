package com.mercadolibre.mutants.services.detector.counter.sequence;

import com.mercadolibre.mutants.services.detector.counter.tokens.StringCounterService;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class MutantMatchByColumnImpl implements MutantMatcherService {
    @Override
    public int countMutantPatternMatch(String[] dna, StringCounterService stringCounterService, int count, int minOccurrences, int minLength) {
        return IntStream.range(0, dna.length).parallel().mapToObj(i ->
                IntStream.range(0, dna[i].length()).parallel().map(j ->
                        dna[j].charAt(i)
                ).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString())
                .mapToInt(s -> stringCounterService.countMatchesOnString(s,count,minOccurrences,minLength)).sum();
    }
}
