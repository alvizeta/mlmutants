package com.mercadolibre.mutants;

import com.mercadolibre.mutants.services.detector.MutantDetectorImpl;
import com.mercadolibre.mutants.exceptions.InvalidDnaChainException;
import com.mercadolibre.mutants.model.Stats;
import com.mercadolibre.mutants.services.analisis.DnaAnalisysService;
import com.mercadolibre.mutants.services.stats.StatsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MutantsApplicationTests {

    @Autowired
    MutantDetectorImpl mutantDetector;

    @Autowired
    DnaAnalisysService dnaAnalisysService;

    @Autowired
    StatsService statsService;


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static int MATRIX_NUMBER = 6;
    private final static char[] ALFABETO = {'A', 'C', 'G', 'T'};

    private static String generateRandomString(int wordLength) {
        Random r = new Random();
        return IntStream.range(0, wordLength).parallel().mapToObj(j ->
                ALFABETO[r.nextInt(ALFABETO.length)])
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    @Test
    public void regexTest () {
        Pattern mutantPattern = Pattern.compile(
                                        "(?:[ACTG]*)((A{" + 4 + ",})|(C{" + 4 + ",})|(G{" + 4 + ",})|(T{" + 4 + ",}))+(?:[ACTG]*)",
                Pattern.CASE_INSENSITIVE);

        String strtest = "AGGTATGGGAAATGGGGAGGGGTAAAAGTAAAATTTT";
        String strtest1 = "AAAATTTT";

        logger.info("String [AGGTATGGGAAATAGGGGTAAAAGTAAAAGGGGTTTT]");
        int counter = 0;

        Matcher matcher = mutantPattern.matcher(strtest1);

        logger.info("Counter matchgroup -> " + matcher.groupCount());
        while (matcher.find()){
            counter++;
            logger.info("counter -> " + counter);
        }
    }

    @Test
    public void testKeyDuplication() {
        Stats stats;

        for (int i = 0; i < 2; i++) {
            long startOptimized = System.currentTimeMillis();

            stats = statsService.getStats();

            long stopOptimized = System.currentTimeMillis();

            logger.info(stopOptimized - startOptimized + " ms taken by StatsService -> " + stats);
        }
    }

    @Test
    public void testMutantDetector() throws InvalidDnaChainException {

        String[] randomDna = new String[MATRIX_NUMBER];
        boolean isMutant;
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < MATRIX_NUMBER; j++) {
                randomDna[j] = generateRandomString(MATRIX_NUMBER);
            }

            logger.info("Generated random DNA for test -> \n[" + String.join("\n", randomDna) + "]");

            long startOptimized = System.currentTimeMillis();

            isMutant = mutantDetector.isMutant(randomDna);

            long stopOptimized = System.currentTimeMillis();

            logger.info(stopOptimized - startOptimized + " ms taken by isMutant -> " + isMutant);

            logger.info("Statics -> " + statsService.getStats());
        }

        logger.info("DNA mutante de prueba -> " + mutantDetector.isMutant(dna));

        Assert.assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    public void testSimpleMutantDetector() throws InvalidDnaChainException {

        boolean isMutant = false;
        String[] dna = {"ATGCGA",
                        "CAGTGC",
                        "TTATGT",
                        "AGAAGG",
                        "CCCCTA",
                        "TCACTG"};

        isMutant = mutantDetector.isMutant(dna);

        System.out.println(isMutant);

    }

    @Test
    public void testDnaService() throws InvalidDnaChainException {

        logger.info("DNA SERVICE");
        String[] randomDna = new String[MATRIX_NUMBER];
        boolean isMutant;
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < MATRIX_NUMBER; j++) {
                randomDna[j] = generateRandomString(MATRIX_NUMBER);
            }

            logger.info("Generated random DNA for test -> \n[" + String.join("\n", randomDna) + "]");

            long startOptimized = System.currentTimeMillis();

            isMutant = dnaAnalisysService.analyseAndPersistDna(randomDna);

            long stopOptimized = System.currentTimeMillis();

            logger.info(stopOptimized - startOptimized + " ms taken by isMutant -> " + isMutant);

            logger.info("Statics -> " + statsService.getStats());
        }

        logger.info("DNA mutante de prueba -> " + mutantDetector.isMutant(dna));

        logger.info("Statics -> " + statsService.getStats());

        Assert.assertTrue(mutantDetector.isMutant(dna));
    }


}


