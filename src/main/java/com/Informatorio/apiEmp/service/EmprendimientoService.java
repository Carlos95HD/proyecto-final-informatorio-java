package com.Informatorio.apiEmp.service;

import javax.persistence.EntityNotFoundException;

import com.Informatorio.apiEmp.dto.OperacionEmprendimiento;
import com.Informatorio.apiEmp.dto.UserTypesEnum;
import com.Informatorio.apiEmp.entity.Emprendimiento;
import com.Informatorio.apiEmp.entity.Usuario;
import com.Informatorio.apiEmp.repository.EmprendimientoRepository;
import com.Informatorio.apiEmp.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmprendimientoService {

    private EmprendimientoRepository emprendimientoRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public EmprendimientoService(EmprendimientoRepository emprendimientoRepository,
            UsuarioRepository usuarioRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Emprendimiento crearEmprendimiento(OperacionEmprendimiento operacionEmprendimiento, Long idUser) {
        Usuario usuario = usuarioRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("No existe usuario"));
        Emprendimiento emprendimiento = new Emprendimiento();
        emprendimiento.setOwner(usuario);
        emprendimiento.getOwner().setTipoUsuario(UserTypesEnum.OWNER);
        emprendimiento.setContenido(operacionEmprendimiento.getContenido());
        emprendimiento.setNombre(operacionEmprendimiento.getNombre());
        emprendimiento.setDescripcion(operacionEmprendimiento.getDescripcion());
        emprendimiento.setObjetivo(operacionEmprendimiento.getObjetivo());
        emprendimiento.setPublicado(operacionEmprendimiento.getPublicado());
        emprendimiento.setUrl(operacionEmprendimiento.getUrl());
        emprendimiento.setTags(operacionEmprendimiento.getTags());
        usuario.getEmprendimientos().add(emprendimiento);

        return emprendimientoRepository.save(emprendimiento);
    }

}