package com.Informatorio.apiEmp.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String generadoDesde;
    private String username;
    @JsonIgnore
    private Long usernameId;
    private String emprendimientoVotado;
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
    public String getUsername() {
        return username;
    }
    public void setUsername(Usuario username) {
        this.username = username.getEmail();
    }
    public Long getUsernameId() {
        return usernameId;
    }
    public void setUsernameId(Long usernameId) {
        this.usernameId = usernameId;
    }
    public String getEmprendimientoVotado() {
        return emprendimientoVotado;
    }
    public void setEmprendimientoVotado(String emprendimientoVotado) {
        this.emprendimientoVotado = emprendimientoVotado;
    }
    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }
    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }
    @Override
    public String toString() {
        return "Voto [emprendimientoVotado=" + emprendimientoVotado + ", fechaDeCreacion=" + fechaDeCreacion
                + ", generadoDesde=" + generadoDesde + ", id=" + id + ", username=" + username + ", usernameId="
                + usernameId + "]";
    }
}
