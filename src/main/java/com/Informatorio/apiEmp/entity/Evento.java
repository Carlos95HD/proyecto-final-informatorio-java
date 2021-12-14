package com.Informatorio.apiEmp.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.PreRemove;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.Informatorio.apiEmp.dto.EventoEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Detalle no puede estar vacio")
    private String detalles;

    @CreationTimestamp
    private Date fechaDeCreacion;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaDeCierre;
    private EventoEnum estadoEvento = EventoEnum.ABIERTO;

    @OrderBy("contadorVotos DESC")
    @JsonIgnoreProperties({ "descripcion", "contenido", "fechaDeCreacion", "objetivo", "publicado", "tags", "url" })
    @ManyToMany(mappedBy = "eventos")
    private List<Emprendimiento> emprendimientos = new ArrayList<>();

    @NotNull
    private BigDecimal premio;

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

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public Date getFechaDeCierre() {
        return fechaDeCierre;
    }

    public void setFechaDeCierre(Date fechaDeCierre) {
        this.fechaDeCierre = fechaDeCierre;
    }

    public EventoEnum getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(EventoEnum estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    public List<Emprendimiento> getEmprendimientos() {
        return emprendimientos;
    }

    public void setEmprendimientos(List<Emprendimiento> emprendimientos) {
        this.emprendimientos = emprendimientos;
    }

    public void addEmprendimiento(Emprendimiento emprendimiento) {
        this.emprendimientos.add(emprendimiento);
        emprendimiento.getEventos().add(this);
    }

    @PreRemove
    public void removeEmprendimientos() {
        for (Emprendimiento emprendimiento : emprendimientos) {
            emprendimiento.getEventos().remove(this);
        }
    }

    public BigDecimal getPremio() {
        return premio;
    }

    public void setPremio(BigDecimal premio) {
        this.premio = premio;
    }

    @Override
    public String toString() {
        return "Evento [detalles=" + detalles + ", emprendimientos=" + emprendimientos + ", estadoEvento="
                + estadoEvento + ", fechaDeCierre=" + fechaDeCierre + ", fechaDeCreacion=" + fechaDeCreacion + ", id="
                + id + ", premio=" + premio + "]";
    }
}
