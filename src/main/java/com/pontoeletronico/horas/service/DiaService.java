package com.pontoeletronico.horas.service;

import com.pontoeletronico.horas.entity.Dia;
import com.pontoeletronico.horas.entity.Hora;
import com.pontoeletronico.horas.repository.DiaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DiaService {

    private static Logger log = LoggerFactory.getLogger(DiaService.class);

    @Autowired
    DiaRepository diaRepository;

    public Dia salvarDia(Dia dia) {
        return diaRepository.save(dia);
    }

    public List<Dia> listar() {
        return diaRepository.findAll();
    }

    public Dia buscarPorId(Long id) {
        return diaRepository.getOne(id);
    }

    public Optional<Dia> buscarPorId2(Long id) {
        return diaRepository.findById(id);
    }

    public void removerDia(Long id) {
        diaRepository.deleteById(id);
    }

    /*public Date obterHora(Long id) {
        SimpleDateFormat FORMAT = new SimpleDateFormat("HH:mm");

        Dia dia = buscarPorId(id);

        log.info("Primeira Hora %s ", FORMAT.format(dia.getHoraDeEntrada()));
        log.info("Segunda  Hora %s ", FORMAT.format(dia.getHoraDeSaidaAlmoco()));
        log.info("Terceira Hora %s ", FORMAT.format(dia.getHoraDeEntradaAlmoco()));
        log.info(" Quarta  Hora %s ", FORMAT.format(dia.getHoraDeSaida()));

        Calendar horaDeEntrada = Calendar.getInstance();
        Calendar horaDeSaidaAlmoco = Calendar.getInstance();
        Calendar horaDeEntradaAlmoco = Calendar.getInstance();
        Calendar horaDeSaida = Calendar.getInstance();

        horaDeEntrada.setTime(dia.getHoraDeEntrada());
        horaDeSaidaAlmoco.setTime(dia.getHoraDeSaidaAlmoco());
        horaDeEntradaAlmoco.setTime(dia.getHoraDeEntradaAlmoco());
        horaDeSaida.setTime(dia.getHoraDeSaida());


        Long diferencaMili = horaDeSaidaAlmoco.getTimeInMillis() - horaDeEntrada.getTimeInMillis();
        Hora hr = getDif(diferencaMili);

        diferencaMili = horaDeSaida.getTimeInMillis() - horaDeEntradaAlmoco.getTimeInMillis();
        Hora hr1 = getDif(diferencaMili);

        Calendar data = hr.obterInstancia(hr1);

        return data.getTime();

    }*/

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
