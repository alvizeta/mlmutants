package com.mercadolibre.mutants.services.detector.counter.tokens;

import org.springframework.stereotype.Service;


@Service
public class StringCounterServiceImpl implements StringCounterService {
    @Override
    public int countMatchesOnString(String dna, int count, int minOccurrences, int minMatchs) {
        char currentChar;
        char lastChar;
        int matchCounter = 0;

        int localCounter = count;

        int i = 0;
        int j = 1;

        while ( j < dna.length() && localCounter <= minOccurrences) {
            lastChar = dna.charAt(i);
            currentChar = dna.charAt(j);

            if(lastChar == currentChar){
                matchCounter++;
                if(matchCounter == minMatchs-1){
                    localCounter++;
                }
            } else {
                matchCounter = 0;
            }
            i++;
            j++;
        }

        return localCounter;
    }
}
