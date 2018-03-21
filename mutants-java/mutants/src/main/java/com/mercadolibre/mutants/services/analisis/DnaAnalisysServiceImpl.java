package com.mercadolibre.mutants.services.analisis;

import com.mercadolibre.mutants.dao.DnaEntity;
import com.mercadolibre.mutants.dao.DnaRepository;
import com.mercadolibre.mutants.services.detector.MutantDetector;
import com.mercadolibre.mutants.exceptions.InvalidDnaChainException;
import com.mercadolibre.mutants.services.stats.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DnaAnalisysServiceImpl implements DnaAnalisysService {

    final private MutantDetector mutantDetector;
    final private DnaRepository dnaRepository;
    final private StatsService statsService;

    @Autowired
    public DnaAnalisysServiceImpl(MutantDetector mutantDetector, DnaRepository dnaRepository, StatsService statsService) {
        this.mutantDetector = mutantDetector;
        this.dnaRepository = dnaRepository;
        this.statsService = statsService;
    }

    @Override
    @Transactional
    public boolean analyseAndPersistDna(String[] dna) throws InvalidDnaChainException {
        String stringDna = String.join(",",dna);
        DnaEntity daoDnaEntity;
        Long id = (long) stringDna.hashCode();

        if(dnaRepository.existsById(id)){
            daoDnaEntity = dnaRepository.getOne(id);
        } else {
            daoDnaEntity = new DnaEntity();
            daoDnaEntity.setDnaHashCode(id);
            daoDnaEntity.setDna(stringDna);
            daoDnaEntity.setMutant(mutantDetector.isMutant(dna));
            dnaRepository.save(daoDnaEntity);

            if (daoDnaEntity.isMutant()) {
                statsService.incrementNumberOfMutants();
            } else {
                statsService.incrementNumberOfHummans();
            }
        }

        return daoDnaEntity.isMutant();
    }
}
