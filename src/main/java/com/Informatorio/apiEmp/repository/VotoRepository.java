package com.Informatorio.apiEmp.repository;

import com.Informatorio.apiEmp.entity.Voto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long>{
}
