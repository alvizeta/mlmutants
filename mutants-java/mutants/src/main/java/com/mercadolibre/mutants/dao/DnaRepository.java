package com.mercadolibre.mutants.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DnaRepository extends JpaRepository<DnaEntity, Long>{
    List<DnaEntity> findByIsMutant(boolean isMutant);
}
