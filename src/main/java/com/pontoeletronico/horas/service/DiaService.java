package com.pontoeletronico.horas.service;

import com.pontoeletronico.horas.entity.Dia;
import com.pontoeletronico.horas.entity.Hora;
import com.pontoeletronico.horas.repository.DiaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DiaService {

    private static Logger log = LoggerFactory.getLogger(DiaService.class);

    @Autowired
    DiaRepository diaRepository;

    @PersistenceContext
    EntityManager em;

    public Dia salvarDia(Dia dia) {
        return diaRepository.save(dia);
    }

    public List<Dia> listar() {
        return diaRepository.findAll();
    }

    public Optional<Dia> buscarPorId2(Long id) {
        return diaRepository.findById(id);
    }

    public Dia buscarPorDataDoDia(LocalDate dataDoDia) {
       return diaRepository.findByDataDoDia(dataDoDia);
    }

    public void removerDia(Long id) {
        diaRepository.deleteById(id);
    }

    public LocalTime obterHora(LocalDate dataDoDia) {
        SimpleDateFormat FORMAT = new SimpleDateFormat("HH:mm");

        Dia dia = buscarPorDataDoDia(dataDoDia);

        log.info("Primeira Hora %s ", FORMAT.format(dia.getHoraDeEntrada()));
        log.info("Segunda  Hora %s ", FORMAT.format(dia.getHoraDeSaidaAlmoco()));
        log.info("Terceira Hora %s ", FORMAT.format(dia.getHoraDeEntradaAlmoco()));
        log.info(" Quarta  Hora %s ", FORMAT.format(dia.getHoraDeSaida()));

        Integer diferenca = (dia.getHoraDeSaidaAlmoco().toSecondOfDay() - dia.getHoraDeEntrada().toSecondOfDay()) +
                (dia.getHoraDeSaida().toSecondOfDay() - dia.getHoraDeEntradaAlmoco().toSecondOfDay());

        return LocalTime.ofSecondOfDay(diferenca);
    }

    private static Hora getDif(long difMilli) {
        int timeInSeconds = (int) difMilli / 1000;
        int hours = timeInSeconds / 3600;
        timeInSeconds -= (hours * 3600);
        int minutes = timeInSeconds / 60;
        timeInSeconds -= (minutes * 60);
        int seconds = timeInSeconds;

        Hora hora = new Hora(hours, minutes, seconds);
        return hora;
    }

}
