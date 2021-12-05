package com.Informatorio.apiEmp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
    private BigDecimal objetivo;
    private Boolean publicado;
    private String url;
    private ArrayList<String> tags = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Usuario usuario;

    public Emprendimiento () {
    }
    public Emprendimiento(Long id, String nombre, String descripcion, String contenido, LocalDateTime fechaDeCreacion,
    BigDecimal objetivo, Boolean publicado, String url, ArrayList<String> tags, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.fechaDeCreacion = fechaDeCreacion;
        this.objetivo = objetivo;
        this.publicado = publicado;
        this.url = url;
        this.tags = tags;
        this.usuario = usuario;
    }

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
    public String obtenerTagsString() {
        String tagsString = "";
        for (String tag : tags) {
            tagsString += tag.toLowerCase() + ",";
        }

        return tagsString;
    }
    public void ObtenerTagsString() {
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Emprendimiento [contenido=" + contenido + ", descripcion=" + descripcion + ", fechaDeCreacion="
                + fechaDeCreacion + ", id=" + id + ", nombre=" + nombre + ", objetivo=" + objetivo + ", publicado="
                + publicado + ", url=" + url + ", usuario=" + usuario + "]";
    }
}
