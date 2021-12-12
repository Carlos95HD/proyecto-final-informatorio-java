package com.Informatorio.apiEmp.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotEmpty;

public class OperacionEmprendimiento {

    @NotEmpty(message = "El nombre no puede ser vacio")
    private String nombre;

    @NotEmpty(message = "El descripcion no puede ser vacio")
    private String descripcion;
    private String contenido;
    private BigDecimal objetivo;
    private Boolean publicado;
    private String url;
    private List<Long> tags;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public BigDecimal getObjetivo() {
        return objetivo;
    }
    public void setObjetivo(BigDecimal objetivo) {
        this.objetivo = objetivo;
    }
    public Boolean getPublicado() {
        return publicado;
    }
    public void setPublicado(Boolean publicado) {
        this.publicado = publicado;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public List<Long> getTags() {
        return tags;
    }
    public void setTags(List<Long> tags) {
        this.tags = tags;
    }
}
