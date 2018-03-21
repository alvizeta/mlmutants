package com.mercadolibre.mutants.services.detector;

import com.mercadolibre.mutants.exceptions.InvalidDnaChainException;
import com.mercadolibre.mutants.services.detector.counter.sequence.MutantMatchByColumnImpl;
import com.mercadolibre.mutants.services.detector.counter.sequence.MutantMatchByDiagonalImpl;
import com.mercadolibre.mutants.services.detector.counter.sequence.MutantMatchByRowImpl;
import com.mercadolibre.mutants.services.detector.counter.sequence.MutantMatcherService;
import com.mercadolibre.mutants.services.detector.counter.tokens.StringCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class MutantDetectorImpl implements MutantDetector {

    private static final int MIN_REPETITION = 4;
    private static final int MIN_MATCHES = 2;

    private static final Pattern VALID_DNA_PATTERN = Pattern.compile(
            "[ACTG]+",
            Pattern.CASE_INSENSITIVE);

    private final MutantMatcherService[] mutantDnaMatchCounters;

    private final StringCounterService stringCounterService;

    @Autowired
    public MutantDetectorImpl(MutantMatchByRowImpl mutantMatchByRowImpl,
                              MutantMatchByColumnImpl mutantMatchByColumnImpl,
                              MutantMatchByDiagonalImpl mutantMatchByDiagonalImpl,
                              StringCounterService stringCounterService) {

        this.stringCounterService = stringCounterService;
        this.mutantDnaMatchCounters = new MutantMatcherService[]{mutantMatchByRowImpl, mutantMatchByColumnImpl,mutantMatchByDiagonalImpl};

    }

    @Override
    public boolean isMutant(String[] dna) throws InvalidDnaChainException {

        if (!checkValidDNA(dna, VALID_DNA_PATTERN)) {
            throw new InvalidDnaChainException("The DNA doesn't have an appropiate format -> [" +
                    String.join(",", dna) + "]");
        }

        int countedMutant = 0;

        for (int i = 0; i < this.mutantDnaMatchCounters.length && countedMutant <= MIN_MATCHES; i++) {
                countedMutant = this.mutantDnaMatchCounters[i]
                        .countMutantPatternMatch(dna,
                                stringCounterService,
                                countedMutant,
                                MIN_MATCHES,
                                MIN_REPETITION);
        }

        return countedMutant >= MIN_MATCHES;
    }

    private boolean checkValidDNA(String[] dna, Pattern validDnaPattern) {
        return Stream.of(dna)
                .filter(e -> (e.isEmpty() || e.length() != dna.length || !(validDnaPattern.matcher(e).matches())))
                .limit(1)
                .count() == 0;
    }
}
