package com.Informatorio.apiEmp.repository;

import com.Informatorio.apiEmp.entity.Evento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}
