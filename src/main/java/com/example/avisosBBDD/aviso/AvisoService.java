package com.example.avisosBBDD.aviso;

import com.example.avisosBBDD.aviso.repository.AvisoRepository;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class AvisoService {
    private final AvisoRepository bbdd;

    public AvisoService(AvisoRepository bbdd) {
        this.bbdd = bbdd;
    }

    /**
     * CREA UN NUEVO AVISO.
     * Nota: al crear el aviso se pone activo = true
     *
     * @param avisoRequest Datos del usuario para crear el aviso (titulo, mensaje, nivel)
     * @return El aviso creado y guardado en BBDD
     */
    public Aviso crear(AvisoRequest avisoRequest){
        Aviso aviso = new Aviso();

        aviso.setMensaje(avisoRequest.getMensaje());
        aviso.setNivel(avisoRequest.getNivel());
        aviso.setTitulo(avisoRequest.getTitulo());
        aviso.setActivo(true);

        return bbdd.save(aviso);
    }

    /**
     * LISTA TODOS LOS AVISOS
     *
     * @return Devuelve la lista de todos los avisos (sin filtro)
     */
    public List<Aviso> listar(){
        List<Aviso> avisos = bbdd.findAll();
        return avisos;
    }

    /**
     * LISTA LOS AVISOS ACTIVOS
     *
     * @return Devuelve una lista con los avisos.
     */
    public List<Aviso> listarActivos(){
        return bbdd.findByActivoTrue();
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
    public Aviso cambiarNivel(Integer id, Nivel nuevoNivel){
        Aviso aviso = bbdd.findById(id).orElse(null);
        if (aviso == null ) return null;

        aviso.setNivel(nuevoNivel);
        return bbdd.save(aviso);
    }

    /**
     * OBTENER AVISO POR ID
     *
     * @param id ID de la tarea deseada
     */
    public Aviso obtener(int id){
        return bbdd.findById(id).orElse(null);
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
    public Aviso reemplazar(int id, AvisoRequest avisoRequest){
        Aviso aviso = bbdd.findById(id).orElse(null);
        if(aviso == null) return null;

        aviso.setNivel(avisoRequest.getNivel());
        aviso.setTitulo(avisoRequest.getTitulo());
        aviso.setMensaje(avisoRequest.getMensaje());
        return bbdd.save(aviso);

    }

    /**
     * REEMPLAZO PARCIAL DE PARAMETROS
     *
     * @param id
     * @param avisoRequest
     * @return
     */
    public Aviso reemplazoParcial(int id, AvisoRequest avisoRequest){
        Aviso aviso = bbdd.findById(id).orElse(null);
        if (aviso == null) return null;

        if(avisoRequest.getMensaje() != null)
            aviso.setMensaje(avisoRequest.getMensaje());

        if(avisoRequest.getTitulo() != null)
            aviso.setTitulo(avisoRequest.getTitulo());

        if(avisoRequest.getNivel() != null)
            aviso.setNivel(avisoRequest.getNivel());

        return bbdd.save(aviso);
    }
}