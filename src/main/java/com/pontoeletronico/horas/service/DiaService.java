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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    public Date obterHora(LocalDate dataDoDia) {
        SimpleDateFormat FORMAT = new SimpleDateFormat("HH:mm");

        Dia dia = buscarPorDataDoDia(dataDoDia);

        log.info("Primeira Hora %s ", FORMAT.format(dia.getHoraDeEntrada()));
        log.info("Segunda  Hora %s ", FORMAT.format(dia.getHoraDeSaidaAlmoco()));
        log.info("Terceira Hora %s ", FORMAT.format(dia.getHoraDeEntradaAlmoco()));
        log.info(" Quarta  Hora %s ", FORMAT.format(dia.getHoraDeSaida()));

        Calendar horaDeEntrada1 = Calendar.getInstance();
        Calendar horaDeSaidaAlmoco2 = Calendar.getInstance();
        Calendar horaDeEntradaAlmoco3 = Calendar.getInstance();
        Calendar horaDeSaida4 = Calendar.getInstance();

        Instant instante1 = LocalDate.EPOCH.atTime(dia.getHoraDeEntrada()).atZone(ZoneId.systemDefault()).toInstant();
        Instant instante2 = LocalDate.EPOCH.atTime(dia.getHoraDeSaidaAlmoco()).atZone(ZoneId.systemDefault()).toInstant();
        Instant instante3 = LocalDate.EPOCH.atTime(dia.getHoraDeEntradaAlmoco()).atZone(ZoneId.systemDefault()).toInstant();
        Instant instante4 = LocalDate.EPOCH.atTime(dia.getHoraDeSaida()).atZone(ZoneId.systemDefault()).toInstant();



        horaDeEntrada1.setTime(Date.from(instante1));
        horaDeSaidaAlmoco2.setTime(Date.from(instante2));
        horaDeEntradaAlmoco3.setTime(Date.from(instante3));
        horaDeSaida4.setTime(Date.from(instante4));


        Long diferencaMili = horaDeSaidaAlmoco2.getTimeInMillis() - horaDeEntrada1.getTimeInMillis();
        Hora hr = getDif(diferencaMili);

        diferencaMili = horaDeSaida4.getTimeInMillis() - horaDeEntradaAlmoco3.getTimeInMillis();
        Hora hr1 = getDif(diferencaMili);

        Calendar data = hr.obterInstancia(hr1);

        return data.getTime();
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
