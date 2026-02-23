package com.example.avisosBBDD.aviso.entity;

import com.example.avisosBBDD.aviso.model.Nivel;
import jakarta.persistence.*;
import com.example.avisosBBDD.aviso.entity.Usuario;

@Entity
@Table(name = "avisos")
public class Aviso {

    @Id // le indica a JPA que este es el valor del ID de la BBDD
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //@GeneratedValue --> indica al Spring que ese valor no lo genera el
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String mensaje;

    @Enumerated(EnumType.STRING)        // Este campo es un enum en Java y quiero que en la base de datos se guarde como texto, usando el nombre del enum.”
    @Column(nullable = false)
    private Nivel nivel;

    @Column(nullable = false)
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @Column (nullable = false)
    private Usuario usuario;


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public boolean isActivo() { return activo;   }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
