package com.Informatorio.apiEmp.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.Informatorio.apiEmp.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    List<Usuario> findByCiudad(@Param("ciudad") String ciudad );
    List<Usuario> findByFechaDeCreacionAfter(LocalDateTime fechaDeCreacion);
    List<Usuario> findByEmail(String email);
}