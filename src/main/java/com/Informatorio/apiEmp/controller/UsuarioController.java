package com.Informatorio.apiEmp.controller;


import java.time.LocalDate;

import javax.validation.Valid;

import com.Informatorio.apiEmp.entity.Usuario;
import com.Informatorio.apiEmp.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public ResponseEntity<?> getUsuarios(
        @RequestParam(required=false) String ciudad,
        @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate creado){
            if (ciudad != null) {
                return new ResponseEntity<>(usuarioRepository.findByCiudad(ciudad), HttpStatus.OK);
            } else if (creado != null) {
                return new ResponseEntity<>(usuarioRepository.findByFechaDeCreacionAfter(creado.atStartOfDay()), HttpStatus.OK);
            }

            return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody @Valid Usuario usuario) {
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }
}