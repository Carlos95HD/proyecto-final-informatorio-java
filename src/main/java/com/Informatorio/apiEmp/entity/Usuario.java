package com.Informatorio.apiEmp.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.persistence.CascadeType;

import com.Informatorio.apiEmp.dto.UserTypesEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotEmpty(message = "Nombre no puede estar vacio")
    private String nombre;
    
    @NotBlank
    @NotEmpty(message = "Apellido no puede estar vacio")
    private String apellido;

    @NotEmpty(message = "Email no puede estar vacio")
    @Column(unique = true)
    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;

    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;

    @NotEmpty(message = "Password no puede estar vacio")
    @NotBlank
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @NotEmpty(message = "Ciudad no puede estar vacio")
    private String ciudad;

    @NotEmpty(message = "Provincia no puede estar vacio")
    private String provincia;

    @NotEmpty(message = "Pais no puede estar vacio")
    private String pais;
    private UserTypesEnum tipoUsuario = UserTypesEnum.USUARIO;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Emprendimiento> emprendimientos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public UserTypesEnum getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(UserTypesEnum tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<Emprendimiento> getEmprendimientos() {
        return emprendimientos;
    }

    public void setEmprendimientos(List<Emprendimiento> emprendimiento) {
        this.emprendimientos = emprendimiento;
    }

    @Override
    public String toString() {
        return "Usuario [apellido=" + apellido + ", ciudad=" + ciudad + ", email=" + email + ", emprendimientos="
                + emprendimientos + ", fechaDeCreacion=" + fechaDeCreacion + ", id=" + id + ", nombre=" + nombre
                + ", pais=" + pais + ", password=" + password + ", provincia=" + provincia + ", tipoUsuario="
                + tipoUsuario + "]";
    }
}