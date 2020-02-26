package com.pontoeletronico.horas.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pontoeletronico.util.EntidadeAbstrata;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Dia extends EntidadeAbstrata {

    @Column(unique = true)
    private LocalDate dataDoDia;

    private LocalTime horaDeEntrada;
    private LocalTime horaDeSaidaAlmoco;
    private LocalTime horaDeEntradaAlmoco;
    private LocalTime horaDeSaida;

}