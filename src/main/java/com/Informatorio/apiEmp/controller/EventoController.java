package com.Informatorio.apiEmp.controller;

import javax.validation.Valid;

import com.Informatorio.apiEmp.entity.Evento;
import com.Informatorio.apiEmp.repository.EventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/evento")
public class EventoController {

    EventoRepository eventoRepository;

    @Autowired
    public EventoController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsuarios() {
        return new ResponseEntity<>(eventoRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody @Valid Evento evento) {
        return new ResponseEntity<>(eventoRepository.save(evento), HttpStatus.CREATED);
    }
}
