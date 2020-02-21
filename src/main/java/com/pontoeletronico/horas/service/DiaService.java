package com.pontoeletronico.horas.service;

import com.pontoeletronico.horas.entity.Dia;
import com.pontoeletronico.horas.entity.Hora;
import com.pontoeletronico.horas.repository.DiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class DiaService {

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

    public void removerDia(Dia dia) {
        diaRepository.delete(dia);
    }

    public void obterHora() {
        SimpleDateFormat FORMAT = new SimpleDateFormat("HH:mm");

        for (Dia dia : this.listar()) {
            System.out.println("Primeira Hora: " + FORMAT.format(dia.getHoraDeEntrada()));
            System.out.println("Segunda  Hora: " + FORMAT.format(dia.getHoraDeSaidaAlmoco()));
            System.out.println("Terceira Hora: " + FORMAT.format(dia.getHoraDeEntradaAlmoco()));
            System.out.println(" Quarta  Hora: " + FORMAT.format(dia.getHoraDeSaida()));

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

            System.out.println("Hora:" +FORMAT.format(data.getTime()));

        }
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
