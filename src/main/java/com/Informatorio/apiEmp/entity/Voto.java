package com.Informatorio.apiEmp.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String generadoDesde;
    private String usuario;
    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getGeneradoDesde() {
        return generadoDesde;
    }
    public void setGeneradoDesde(String generadoDesde) {
        this.generadoDesde = generadoDesde;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }
    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }
    @Override
    public String toString() {
        return "Voto [fechaDeCreacion=" + fechaDeCreacion + ", generadoDesde=" + generadoDesde + ", id=" + id
                + ", usuario=" + usuario + "]";
    }
}
