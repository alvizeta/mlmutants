package com.mercadolibre.mutants.services.stats;

import com.mercadolibre.mutants.dao.DnaRepository;
import com.mercadolibre.mutants.model.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class StatsServiceImpl implements StatsService {

    private final Stats stats;
    private final DnaRepository dnaRepository;

    @Autowired
    public StatsServiceImpl(DnaRepository dnaRepository) {
        this.stats = new Stats();
        this.dnaRepository = dnaRepository;
    }

    @Override
    public void incrementNumberOfMutants() {
        stats.incrementCountedMutants();
    }

    @Override
    public void incrementNumberOfHummans() {
        stats.incrementCountedHumans();
    }

    @Override
    public Stats getStats() {
        return stats;
    }

    private void loadStats() {
        long mutantsCount = dnaRepository.findByIsMutant(true).size();
        long hummanCount = dnaRepository.count() - mutantsCount;
        stats.setCountedMutant(mutantsCount);
        stats.setCountedHumans(hummanCount);

        if (hummanCount == 0) {
            stats.setRatio((double) mutantsCount);
        } else {
            stats.setRatio((double) mutantsCount / hummanCount);
        }
    }

    @PostConstruct
    public void init() {
        loadStats();
    }
}
