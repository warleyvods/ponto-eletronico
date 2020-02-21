package com.pontoeletronico.horas.entity;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
public class Hora {

    private Integer hora;
    private Integer minuto;
    private Integer segundo;

    public Hora(Integer horas, Integer minutos, Integer segundos) {
        this.hora = horas;
        this.minuto = minutos;
        this.segundo = segundos;
    }

    public Calendar obterInstancia(Hora horas) {
        Calendar calendario = Calendar.getInstance();

        calendario.set(Calendar.HOUR_OF_DAY, this.getHora());
        calendario.set(Calendar.MINUTE, getMinuto());
        calendario.set(Calendar.SECOND, getSegundo());

        calendario.add(Calendar.HOUR_OF_DAY, horas.getHora());
        calendario.add(Calendar.MINUTE, horas.getMinuto());
        calendario.add(Calendar.SECOND, horas.getSegundo());
        return calendario;
    }

}
