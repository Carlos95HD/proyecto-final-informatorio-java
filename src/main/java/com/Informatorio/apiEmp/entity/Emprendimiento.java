package com.Informatorio.apiEmp.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Emprendimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String contenido;
    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;
    private Float objetivo;
    private Boolean publicado;
    private String url;
    private ArrayList<String> tags;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }
    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }
    public Float getObjetivo() {
        return objetivo;
    }
    public void setObjetivo(Float objetivo) {
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

    @Override
    public String toString() {
        return "Emprendimiento [contenido=" + contenido + ", descripcion=" + descripcion + ", fechaDeCreacion="
                + fechaDeCreacion + ", id=" + id + ", nombre=" + nombre + ", objetivo=" + objetivo + ", publicado="
                + publicado + ", tags=" + tags + ", url=" + url + "]";
    }
}
