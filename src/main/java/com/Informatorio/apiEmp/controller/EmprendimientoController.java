package com.Informatorio.apiEmp.controller;

import javax.validation.Valid;

import com.Informatorio.apiEmp.entity.Emprendimiento;
import com.Informatorio.apiEmp.repository.EmprendimientoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/emprendimiento")
public class EmprendimientoController {
    
    private EmprendimientoRepository emprendimientoRepository;

    @Autowired
    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsuarios() {
        return new ResponseEntity<>(emprendimientoRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody @Valid Emprendimiento emprendimiento) {
        return new ResponseEntity<>(emprendimientoRepository.save(emprendimiento), HttpStatus.CREATED);
    }
}
