package com.Informatorio.apiEmp.controller;

import javax.validation.Valid;

import com.Informatorio.apiEmp.entity.Voto;
import com.Informatorio.apiEmp.repository.VotoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/voto")
public class VotoController {

    private VotoRepository votoRepository;

    @Autowired
    public VotoController(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsuarios() {
        return new ResponseEntity<>(votoRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody @Valid Voto voto) {
        return new ResponseEntity<>(votoRepository.save(voto), HttpStatus.CREATED);
    }
}
