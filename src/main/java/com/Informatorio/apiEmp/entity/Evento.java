package com.Informatorio.apiEmp.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detalles;
    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;
    private String fechaDeCierre;
    private String estado;
    private String suscriptores;
    private Float premio;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDetalles() {
        return detalles;
    }
    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }
    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }
    public String getFechaDeCierre() {
        return fechaDeCierre;
    }
    public void setFechaDeCierre(String fechaDeCierre) {
        this.fechaDeCierre = fechaDeCierre;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getSuscriptores() {
        return suscriptores;
    }
    public void setSuscriptores(String suscriptores) {
        this.suscriptores = suscriptores;
    }
    public Float getPremio() {
        return premio;
    }
    public void setPremio(Float premio) {
        this.premio = premio;
    }
    @Override
    public String toString() {
        return "Evento [detalles=" + detalles + ", estado=" + estado + ", fechaDeCierre=" + fechaDeCierre
                + ", fechaDeCreacion=" + fechaDeCreacion + ", id=" + id + ", premio=" + premio + ", suscriptores="
                + suscriptores + "]";
    }
}
