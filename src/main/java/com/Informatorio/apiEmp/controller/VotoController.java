package com.Informatorio.apiEmp.controller;


import com.Informatorio.apiEmp.entity.Emprendimiento;
import com.Informatorio.apiEmp.entity.Usuario;
import com.Informatorio.apiEmp.entity.Voto;
import com.Informatorio.apiEmp.repository.EmprendimientoRepository;
import com.Informatorio.apiEmp.repository.UsuarioRepository;
import com.Informatorio.apiEmp.repository.VotoRepository;

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
@RequestMapping(value = "/voto")
public class VotoController {

    private VotoRepository votoRepository;
    private UsuarioRepository usuarioRepository;
    private EmprendimientoRepository emprendimientoRepository;

    @Autowired
    public VotoController(VotoRepository votoRepository, UsuarioRepository usuarioRepository,
            EmprendimientoRepository emprendimientoRepository) {
        this.votoRepository = votoRepository;
        this.usuarioRepository = usuarioRepository;
        this.emprendimientoRepository = emprendimientoRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsuarios() {
        return new ResponseEntity<>(votoRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("user/{id}/emprendimiento/{idEmpr}")
    public ResponseEntity<?> crearVoto(
        @PathVariable("id") Long id,
        @PathVariable("idEmpr") Long idEmpr,
        @RequestBody Voto voto){
            Usuario usuario = usuarioRepository.findById(id).get();
            Emprendimiento emprendimiento = emprendimientoRepository.findById(id).get();

            voto.setUsername(usuario);
            voto.setUsernameId(usuario.getId());
            emprendimiento.sumarVotos();

            votoRepository.save(voto);
            emprendimientoRepository.save(emprendimiento);

            return new ResponseEntity<>(HttpStatus.OK);
    }
}
