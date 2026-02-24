package com.example.avisosBBDD.aviso.service;

import com.example.avisosBBDD.aviso.entity.Usuario;
import com.example.avisosBBDD.aviso.model.AvisoRequest;
import com.example.avisosBBDD.aviso.model.AvisoResponse;
import com.example.avisosBBDD.aviso.model.Nivel;
import com.example.avisosBBDD.aviso.entity.Aviso;
import com.example.avisosBBDD.aviso.repository.AvisoRepository;
import com.example.avisosBBDD.aviso.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Stream;


@Service
public class AvisoService {
    private final AvisoRepository bbdd;
    private final UsuarioRepository ur;

    public AvisoService(AvisoRepository bbdd, UsuarioRepository ur) {
        this.bbdd = bbdd;
        this.ur = ur;
    }

    /**
     * CREA UN NUEVO AVISO.
     * Nota: al crear el aviso se pone activo = true
     *
     * @param avisoRequest Datos del usuario para crear el aviso (titulo, mensaje, nivel)
     * @return El aviso creado y guardado en BBDD
     */
    public AvisoResponse crear(AvisoRequest avisoRequest){
        Aviso aviso = new Aviso();

        aviso.setMensaje(avisoRequest.getMensaje());
        aviso.setNivel(avisoRequest.getNivel());
        aviso.setTitulo(avisoRequest.getTitulo());
        aviso.setActivo(true);


        Usuario usuario = ur
                .findById(avisoRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        aviso.setUsuario(usuario);

        return toResponse(bbdd.save(aviso));
    }

    /**
     * LISTA TODOS LOS AVISOS
     *
     * @return Devuelve la lista de todos los avisos (sin filtro)
     */
    public List<AvisoResponse> listar(){

        // Obtener lista de entidades desde la base de datos
        List<Aviso> avisos = bbdd.findAll();

        // Convertir la lista en un Stream
        Stream<Aviso> streamAvisos = avisos.stream();

        // Transformar cada Aviso en AvisoResponse
        Stream<AvisoResponse> streamResponses = streamAvisos.map(aviso -> toResponse(aviso));

        // Convertir el Stream resultante en una nueva lista
        List<AvisoResponse> resultado = streamResponses.toList();

        // Devolver la lista transformada
        return resultado;
    }

    /**
     * LISTA LOS AVISOS ACTIVOS
     *
     * @return Devuelve una lista con los avisos.
     */
    public List<AvisoResponse> listarActivos(){
        return bbdd.findByActivo(true)
                .stream()
                .map(this::toResponse)
                .toList();
    }


    /**
     * DESACTIVA UN AVISO
     * Regla: si existe, pone activo = false (no borra la fila)
     *
     * @param id ID del registro que se quiere desactivar.
     */
    public Aviso desactivar(Integer id){
        Aviso aviso = bbdd.findById(id).orElse(null);
        if(aviso == null) return null;

        aviso.setActivo(false);
        return bbdd.save(aviso);
    }

    /**
     * CAMBIAR NIVEL PRIORIDAD
     * Regla: si no existe, error controlado (excepción o Optional)
     *
     * @param id Entregar el id del registro a modificar
     * @param nuevoNivel Nuevo valor de Nivel
     */
    public AvisoResponse cambiarNivel(Integer id, Nivel nuevoNivel){
        Aviso aviso = bbdd.findById(id).orElse(null);
        if (aviso == null ) return null;

        aviso.setNivel(nuevoNivel);
        return toResponse(bbdd.save(aviso));
    }

    /**
     * OBTENER AVISO POR ID
     *
     * @param id ID de la tarea deseada
     */
    public AvisoResponse obtener(int id){
        Aviso aviso = bbdd.findById(id).orElse(null);
        if(aviso == null) return null;

        return  toResponse(aviso);
    }

    /**
     * BORRAR AVISO POR ID
     *
     * @param id
     */
    public void borrar(int id){
        bbdd.deleteById(id);
    }

    /**
     * REEMPLZA TODOS LOS CAMPOS DEL AVISO
     *
     * @param id
     * @param avisoRequest
     * @return
     */
    public AvisoResponse reemplazar(int id, AvisoRequest avisoRequest){
        Aviso aviso = bbdd.findById(id).orElse(null);
        if(aviso == null) return null;

        aviso.setNivel(avisoRequest.getNivel());
        aviso.setTitulo(avisoRequest.getTitulo());
        aviso.setMensaje(avisoRequest.getMensaje());
        return toResponse(bbdd.save(aviso));

    }

    /**
     * REEMPLAZO PARCIAL DE PARAMETROS
     *
     * @param id
     * @param avisoRequest
     * @return
     */
    public AvisoResponse reemplazoParcial(int id, AvisoRequest avisoRequest){
        Aviso aviso = bbdd.findById(id).orElse(null);
        if (aviso == null) return null;

        if(avisoRequest.getMensaje() != null)
            aviso.setMensaje(avisoRequest.getMensaje());

        if(avisoRequest.getTitulo() != null)
            aviso.setTitulo(avisoRequest.getTitulo());

        if(avisoRequest.getNivel() != null)
            aviso.setNivel(avisoRequest.getNivel());

        return toResponse(bbdd.save(aviso));
    }

    public AvisoResponse toResponse(Aviso aviso){
        AvisoResponse r = new AvisoResponse();
        r.setId(aviso.getId());
        r.setTitulo(aviso.getTitulo());
        r.setMensaje(aviso.getMensaje());
        r.setNivel(aviso.getNivel());
        r.setActivo(aviso.isActivo());

        return r;
    }

}