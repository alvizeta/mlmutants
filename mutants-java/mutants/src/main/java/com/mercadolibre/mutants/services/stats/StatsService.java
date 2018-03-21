package com.mercadolibre.mutants.services.stats;

import com.mercadolibre.mutants.model.Stats;

public interface StatsService {
    void incrementNumberOfMutants ();
    void incrementNumberOfHummans ();
    Stats getStats();
}
