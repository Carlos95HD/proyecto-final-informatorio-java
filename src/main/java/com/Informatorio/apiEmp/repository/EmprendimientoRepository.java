package com.Informatorio.apiEmp.repository;

import java.util.List;

import com.Informatorio.apiEmp.entity.Emprendimiento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprendimientoRepository extends JpaRepository<Emprendimiento, Long>{
    List<Emprendimiento> findByPublicado(Boolean publicado);
}