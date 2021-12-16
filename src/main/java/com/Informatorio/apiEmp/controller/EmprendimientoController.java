package com.Informatorio.apiEmp.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.Informatorio.apiEmp.dto.OperacionEmprendimiento;
import com.Informatorio.apiEmp.entity.Emprendimiento;
import com.Informatorio.apiEmp.repository.EmprendimientoRepository;
import com.Informatorio.apiEmp.service.EmprendimientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class EmprendimientoController {

    private final EmprendimientoService emprendimientoService;
    private EmprendimientoRepository emprendimientoRepository;

    @Autowired
    public EmprendimientoController(EmprendimientoService emprendimientoService,
            EmprendimientoRepository emprendimientoRepository) {
        this.emprendimientoService = emprendimientoService;
        this.emprendimientoRepository = emprendimientoRepository;
    }

    @RequestMapping(value = "/emprendimientos", method = RequestMethod.GET)
    public ResponseEntity<?> getAllEmprendimientos() {
        return new ResponseEntity<>(emprendimientoRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/emprendimientos/search", method = RequestMethod.GET)
    public ResponseEntity<?> getEmprendimentoByTags(
            @RequestParam(required = false) Long tag,
            @RequestParam(required = false) Boolean publicado) {

        if (tag != null) {
            List<Emprendimiento> emprendimientos = emprendimientoRepository.findAll()
                    .stream()
                    .filter(e -> e.searchTag(tag))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(emprendimientos, HttpStatus.OK);
        } else if (publicado != null) {
            List<Emprendimiento> emprendimientos = emprendimientoRepository.findByPublicado(publicado);
            return new ResponseEntity<>(emprendimientos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/usuarios/{id}/emprendimientos")
    public ResponseEntity<?> crearEmprendimiento(@PathVariable("id") Long id,
            @RequestBody @Valid OperacionEmprendimiento operacionEmprendimiento) {
        return new ResponseEntity<>(emprendimientoService.crearEmprendimiento(operacionEmprendimiento, id),
                HttpStatus.CREATED);
    }

    @PutMapping("/usuarios/{id}/emprendimientos/{idEmp}")
    public ResponseEntity<?> modificarEmprendimiento(
            @PathVariable("id") Long id,
            @PathVariable("idEmp") Long idEmp,
            @RequestBody @Valid OperacionEmprendimiento operacionEmprendimiento) {
        return new ResponseEntity<>(emprendimientoService.editarEmprendimento(operacionEmprendimiento,id,idEmp),HttpStatus.CREATED);
    }

    @DeleteMapping("/usuarios/{id}/emprendimientos/{idEmp}")
    public ResponseEntity<?> deleteEmprendimiento(
            @PathVariable("id") Long id,
            @PathVariable("idEmp") Long idEmp) {
        Emprendimiento emprendimientoExistente = emprendimientoRepository.findById(idEmp)
                .orElseThrow(() -> new EntityNotFoundException("Emprendimiento no existe"));

        if (emprendimientoExistente.getOwner().getId() == id) {
            emprendimientoRepository.deleteById(idEmp);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
