package com.Informatorio.apiEmp.dto;

import java.math.BigDecimal;
import java.util.ArrayList;

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
    public ArrayList<String> getTags() {
        return tags;
    }
    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
    private ArrayList<String> tags = new ArrayList<>();

    @Override
    public String toString() {
        return "OperacionEmprendimiento [contenido=" + contenido + ", descripcion=" + descripcion + ", idUsuario="
                + ", nombre=" + nombre + ", objetivo=" + objetivo + ", publicado=" + publicado + ", tags="
                + tags + ", url=" + url + "]";
    }
}
