package com.pontoeletronico.horas.controller;

import com.pontoeletronico.horas.entity.Dia;
import com.pontoeletronico.horas.service.DiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @DeleteMapping("/remover/{id}")
    public void removerDia(@PathVariable Long id){
        diaService.removerDia(id);
    }

    @GetMapping("/buscar/{id}")
    public Optional<Dia> buscarPorId(@PathVariable Long id){
        return diaService.buscarPorId2(id);
    }

    @GetMapping("/buscar/{id}/obterHora/")
    public LocalTime obterHora(@DateTimeFormat(pattern = "yyyy-MM-dd") Date dataDoDia) {
        return diaService.obterHora(dataDoDia.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

}
