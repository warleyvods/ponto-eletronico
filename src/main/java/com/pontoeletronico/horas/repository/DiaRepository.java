package com.pontoeletronico.horas.repository;

import com.pontoeletronico.horas.entity.Dia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DiaRepository extends JpaRepository<Dia, Long> {

    public Dia findByDataDoDia(LocalDate dataDoDia);

}
