package com.Informatorio.apiEmp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Emprendimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nombre no puede estar vacio")
    private String nombre;

    @NotEmpty(message = "Descripción no puede estar vacio")
    private String descripcion;
    private String contenido;

    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;

    @NotNull
    private BigDecimal objetivo;
    private Boolean publicado = false;
    private String url="";

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "empredimiento_id",
            joinColumns = @JoinColumn(name = "emprendimiento_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();
    @ManyToOne
    @JsonIgnoreProperties({"fechaDeCreacion","provincia","ciudad","pais","email" })
    private Usuario owner;

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
    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    public void agregarTag(Tag tag) {
        tags.add(tag);
        tag.getEmprendimientos().add(this);
    }
    public void removerTag(Tag tag) {
        tags.remove(tag);
        tag.getEmprendimientos().remove(null);
    }
    public boolean searchTag(Long id) {
        Boolean existe = false;
        for (Tag tag : tags) {
            if (tag.getId() == id){
                return existe = true;
            };
        }
        return existe;
    }
    public Usuario getOwner() {
        return owner;
    }
    public void setOwner(Usuario usuario) {
        this.owner = usuario;
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
                + ", url=" + url + ", usuario=" + owner + "]";
    }
}
