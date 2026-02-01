package com.example.avisosBBDD.aviso;

public class AvisoRequest {
    private String titulo;
    private String mensaje;
    private Nivel nivel = Nivel.INFO;

    public AvisoRequest(){}

    public AvisoRequest(String mensaje, Nivel nivel, String titulo) {
        this.mensaje = mensaje;
        this.nivel = nivel;
        this.titulo = titulo;
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
