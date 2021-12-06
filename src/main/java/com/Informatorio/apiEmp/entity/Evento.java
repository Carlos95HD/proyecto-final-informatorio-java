package com.Informatorio.apiEmp.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
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
    private String detalles;
    @CreationTimestamp
    private Date fechaDeCreacion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaDeCierre;
    private EventoEnum estadoEvento;
    @ManyToMany(mappedBy = "eventos")
    @JsonIgnoreProperties({ "id","tags" })
    @OrderBy("contadorVotos DESC")
    private List<Emprendimiento> emprendimientos;

    public Evento(){
    }
    public Evento(Long id, String detalles, Date fechaDeCreacion, Date fechaDeCierre, @NotNull EventoEnum estadoEvento,
            List<Emprendimiento> emprendimientos, BigDecimal premio) {
        this.id = id;
        this.detalles = detalles;
        this.fechaDeCreacion = fechaDeCreacion;
        this.fechaDeCierre = fechaDeCierre;
        this.estadoEvento = estadoEvento;
        this.emprendimientos = emprendimientos;
        this.premio = premio;
    }

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
    public BigDecimal getPremio() {
        return premio;
    }
    public void setPremio(BigDecimal premio) {
        this.premio = premio;
    }
    @Override
    public String toString() {
        return "Evento [detalles=" + detalles + ", estadoEvento=" + estadoEvento + ", fechaDeCierre=" + fechaDeCierre
                + ", fechaDeCreacion=" + fechaDeCreacion + ", id=" + id + ", premio=" + premio + ", suscriptores="
                + "]";
    }
}
