package com.pontoeletronico.horas.entity;

import com.pontoeletronico.util.EntidadeAbstrata;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity
public class Dia extends EntidadeAbstrata {

    private Date horaDeEntrada;
    private Date horaDeSaidaAlmoco;
    private Date horaDeEntradaAlmoco;
    private Date horaDeSaida;

}