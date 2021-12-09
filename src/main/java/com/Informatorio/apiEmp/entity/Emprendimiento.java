package com.Informatorio.apiEmp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Integer contadorVotos = 0;
    @JoinTable(
        name = "events_emprendimientos",
        joinColumns = {@JoinColumn(name = "fk_emprendimientos",nullable = false)},
        inverseJoinColumns = {@JoinColumn(name = "fk_eventos",nullable = false)})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Evento> eventos = new ArrayList<>();

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

    public Integer getContadorVotos() {
        return contadorVotos;
    }
    public void setContadorVotos(Integer contadorVotos) {
        this.contadorVotos = contadorVotos;
    }
    public void sumarVotos() {
        this.contadorVotos += 1;
    }
    public List<Evento> getEventos() {
        return eventos;
    }
    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    @Override
    public String toString() {
        return "Emprendimiento [contadorVotos=" + contadorVotos + ", contenido=" + contenido + ", descripcion="
                + descripcion + ", eventos=" + eventos + ", fechaDeCreacion=" + fechaDeCreacion + ", id=" + id
                + ", nombre=" + nombre + ", objetivo=" + objetivo + ", publicado=" + publicado + ", tags=" + tags
                + ", url=" + url + ", usuario=" + usuario + "]";
    }
}
