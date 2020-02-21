package com.pontoeletronico.horas.controller;

import com.pontoeletronico.horas.entity.Dia;
import com.pontoeletronico.horas.entity.Hora;
import com.pontoeletronico.horas.service.DiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/dia")
public class DiaController {

    @Autowired
    DiaService diaService;




    @PostMapping("/salvar")
    public Dia salvarDia(@RequestBody Dia dia) {
        return diaService.salvarDia(dia);
    }

    @GetMapping("/listar")
    public List<Dia> listarDias(){
        return diaService.listar();
    }

    @DeleteMapping("/remover")
    public void removerDia(@RequestBody Dia dia){
        diaService.removerDia(dia);
    }

    @GetMapping("/buscar/{id}")
    public Dia buscarPorId(@PathVariable Long id){
        return diaService.buscarPorId(id);
    }

    @GetMapping("/obterHora")
    public void obterHora() throws ParseException {
        diaService.obterHora();
    }



}
