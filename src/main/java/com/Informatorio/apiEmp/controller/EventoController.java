package com.Informatorio.apiEmp.controller;

import javax.persistence.EntityNotFoundException;
// import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.Informatorio.apiEmp.entity.Emprendimiento;
import com.Informatorio.apiEmp.entity.Evento;
import com.Informatorio.apiEmp.repository.EmprendimientoRepository;
import com.Informatorio.apiEmp.repository.EventoRepository;
import com.Informatorio.apiEmp.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/evento")
public class EventoController {

    private EventoRepository eventoRepository;
    // private UsuarioRepository usuarioRepository;
    private EmprendimientoRepository emprendimientoRepository;

    @Autowired
    public EventoController(EventoRepository eventoRepository, UsuarioRepository usuarioRepository,
            EmprendimientoRepository emprendimientoRepository) {
        this.eventoRepository = eventoRepository;
        // this.usuarioRepository = usuarioRepository;
        this.emprendimientoRepository = emprendimientoRepository;
    }

    @GetMapping("/ranking")
    public ResponseEntity<?> getAllUsuarios() {
        return new ResponseEntity<>(eventoRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody @Valid Evento evento) {
        return new ResponseEntity<>(eventoRepository.save(evento), HttpStatus.CREATED);
    }

    // Suscripcion Evento
    @PostMapping("suscribe/{id}/empr/{idEmpr}")
    public ResponseEntity<?> suscripcion(
        @PathVariable("id") Long id,
        @PathVariable("idEmpr") Long idEmpr) {

            Emprendimiento emprendimientoExistente = emprendimientoRepository.findById(idEmpr).orElseThrow(() -> new EntityNotFoundException("No existe emprendimiento"));
            Evento eventoExistente = eventoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe evento"));

            eventoExistente.addEmprendimiento(emprendimientoExistente);
            eventoRepository.save(eventoExistente);

            return new ResponseEntity<>(HttpStatus.OK);
    }
}
