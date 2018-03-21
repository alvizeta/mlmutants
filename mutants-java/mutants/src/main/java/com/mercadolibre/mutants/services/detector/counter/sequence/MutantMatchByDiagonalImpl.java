package com.mercadolibre.mutants.services.detector.counter.sequence;

import com.mercadolibre.mutants.services.detector.counter.tokens.StringCounterService;
import org.springframework.stereotype.Service;

@Service
public class MutantMatchByDiagonalImpl implements MutantMatcherService {

    @Override
    public int countMutantPatternMatch(String[] dna, StringCounterService stringCounterService, int count, int minOccurrences, int minLength) {

        StringBuilder stringBuilder = new StringBuilder();

        int letfCounter = 0;
        int rightCounter = dna.length - 1;

        int matchesCounter = count;

        while (matchesCounter <= minOccurrences && (dna.length - letfCounter >= minLength && dna.length - rightCounter < minLength)) {

            if (matchesCounter <= minOccurrences) {
                matchesCounter = stringCounterService.countMatchesOnString(getRightDirectionBelowDiagonalToken(dna, letfCounter,stringBuilder),matchesCounter,minOccurrences,minLength);
            }

            if (letfCounter != 0) {
                if (matchesCounter <= minOccurrences) {
                    matchesCounter = stringCounterService.countMatchesOnString(getRightDirectionAboveDiagonalToken(dna, letfCounter, stringBuilder),matchesCounter,minOccurrences,minLength);
                }
            }

            letfCounter++;

            if (rightCounter != dna.length - 1) {
                if (matchesCounter <= minOccurrences) {
                    matchesCounter = stringCounterService.countMatchesOnString(getLeftDirectionAboveDiagonalToken(dna, rightCounter,stringBuilder), matchesCounter,minOccurrences,minLength);
                }
            }

            if (matchesCounter <= minOccurrences) {
                matchesCounter = stringCounterService.countMatchesOnString(getLeftDirectionBelowDiagonalToken(dna, rightCounter, stringBuilder),matchesCounter,minOccurrences,minLength);
            }

            rightCounter--;
        }
        return matchesCounter;
    }


    private String getRightDirectionAboveDiagonalToken(String[] dna, int startIndex, StringBuilder stringBuilder) {
        stringBuilder.setLength(0);
        stringBuilder.trimToSize();

        for (int i = 0, j = startIndex; j < dna.length; j++, i++) {
            stringBuilder.append(dna[j].charAt(i));
        }
        return stringBuilder.toString();
    }

    private String getRightDirectionBelowDiagonalToken(String[] dna, int startIndex, StringBuilder stringBuilder) {
        stringBuilder.setLength(0);
        stringBuilder.trimToSize();

        for (int i = 0, j = startIndex; j < dna.length; j++, i++) {
            stringBuilder.append(dna[i].charAt(j));
        }
        return stringBuilder.toString();
    }


    private String getLeftDirectionAboveDiagonalToken(String[] dna, int startIndex, StringBuilder stringBuilder) {
        stringBuilder.setLength(0);
        stringBuilder.trimToSize();

        for (int i = 0, j = startIndex; j >= 0; j--, i++) {
            stringBuilder.append(dna[j].charAt(i));
        }
        return stringBuilder.toString();
    }

    private String getLeftDirectionBelowDiagonalToken(String[] dna, int startIndex, StringBuilder stringBuilder) {
        stringBuilder.setLength(0);
        stringBuilder.trimToSize();

        for (int i = 0, j = startIndex; j >= 0; j--, i++) {
            stringBuilder.append(dna[dna.length - 1 -i].charAt(dna.length - 1 - j));
        }
        return stringBuilder.toString();
    }

}
