package com.Informatorio.apiEmp.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.Informatorio.apiEmp.entity.Emprendimiento;
import com.Informatorio.apiEmp.entity.Usuario;
import com.Informatorio.apiEmp.repository.EmprendimientoRepository;
import com.Informatorio.apiEmp.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmprendimientoController {

    private EmprendimientoRepository emprendimientoRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository,
            UsuarioRepository usuarioRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @RequestMapping(value = "/emprendimientos", method = RequestMethod.GET)
    public ResponseEntity<?> getAllEmprendimientos() {
        return new ResponseEntity<>(emprendimientoRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/emprendimientos/search", method = RequestMethod.GET)
    public ResponseEntity<?> getEmprendimentoByTags(
        @RequestParam(required=false) String tag,
        @RequestParam(required=false) Boolean publicado){

        if (tag != null) {
            List<Emprendimiento> emprendimientos = emprendimientoRepository.findAll()
                .stream()
                .filter( e -> e.obtenerTagsString().contains(tag.toLowerCase()))
                .collect(Collectors.toList());
            // .filter( e -> e.getTags().contains(tag))
            return new ResponseEntity<>(emprendimientos, HttpStatus.OK);
        } else if (publicado != null){
            List<Emprendimiento> emprendimientos = emprendimientoRepository.findAll()
                .stream()
                .filter( e -> e.getPublicado() == publicado)
                .collect(Collectors.toList());

            return new ResponseEntity<>(emprendimientos, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/usuarios/{id}/emprendimientos")
    public ResponseEntity<?> crearEmprendimiento(@PathVariable("id") Long id,
            @RequestBody @Valid Emprendimiento emprendimiento) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe usuario"));
        emprendimiento.setUsuario(usuario);
        usuario.getEmprendimientos().add(emprendimiento);
        usuarioRepository.save(usuario);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/usuarios/{id}/emprendimientos/{idEmp}")
    public ResponseEntity<?> modificarEmprendimiento(
            @PathVariable("id") Long id,
            @PathVariable("idEmp") Long idEmp,
            @RequestBody @Valid Emprendimiento emprendimiento) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe Usuario"));
        Emprendimiento emprendimientoExistente = emprendimientoRepository.findById(idEmp)
                .orElseThrow(() -> new EntityNotFoundException("Emprendimiento no existe"));

        if (emprendimientoExistente.getUsuario().getId() == id) {
            emprendimientoExistente.setNombre(emprendimiento.getNombre());
            emprendimientoExistente.setUsuario(usuarioExistente);
            return new ResponseEntity<>(emprendimientoRepository.save(emprendimientoExistente), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/usuarios/{id}/emprendimientos/{idEmp}")
    public ResponseEntity<?> deleteEmprendimiento(
            @PathVariable("id") Long id,
            @PathVariable("idEmp") Long idEmp) {
        Emprendimiento emprendimientoExistente = emprendimientoRepository.findById(idEmp)
                .orElseThrow(() -> new EntityNotFoundException("Emprendimiento no existe"));

        if (emprendimientoExistente.getUsuario().getId() == id) {
            emprendimientoRepository.deleteById(idEmp);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
