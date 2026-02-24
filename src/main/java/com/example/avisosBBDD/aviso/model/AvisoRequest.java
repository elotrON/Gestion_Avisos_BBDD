package com.example.avisosBBDD.aviso.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AvisoRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    private String mensaje;

    @NotNull
    private Nivel nivel = Nivel.INFO;

    @NotNull
    private Integer userId = 0;


    public AvisoRequest(){}

    public AvisoRequest(String mensaje, Nivel nivel, String titulo, Integer userId) {
        this.mensaje = mensaje;
        this.nivel = nivel;
        this.titulo = titulo;
        this.userId = userId;
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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


}
