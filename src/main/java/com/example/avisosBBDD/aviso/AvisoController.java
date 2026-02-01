package com.example.avisosBBDD.aviso;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class AvisoController {

    private final AvisoService avisoService;
    public AvisoController(AvisoService avisoService) {
        this.avisoService = avisoService;
    }


    /**
     * CREA UN AVISO NUEVO
     * Uso: POST /avisos
     *
     * @param avisoRequest
     * @return
     */
    @PostMapping("/avisos")
    public Aviso crear(@RequestBody AvisoRequest avisoRequest){
        return avisoService.crear(avisoRequest);
    }

    /**
     * OBTENER TODOS LOS AVISOS
     *
     * GET /avisos
     * @param activos
     * @return
     */
    @GetMapping("/avisos")
    public List<Aviso> obtenerAvisos(@RequestParam(required = false) Boolean activos){
        if(Boolean.TRUE.equals(activos)){
            return avisoService.listarActivos();
        }
        return avisoService.listar();
    }


    /**
     * DEVUELVE EL AVISO DEL [ID] INDICADO
     *
     * @param id Numero de id del registro solicitado
     * @return El objeto de tipo Aviso
     */
    @GetMapping("/avisos/{id}")
    public Aviso getAviso(@PathVariable int id){

        return avisoService.obtener(id);
    }


    /**
     * REEMPLAZA EL AVISO COMPLETO (EXCEPTO EL ID)
     * PUT /avisos/{id}
     *
     *  @param id
     * @param avisoRequest
     * @return
     */
    @PutMapping("/avisos/{id}")
    public Aviso reemplazarAviso(@PathVariable int id, @RequestBody AvisoRequest avisoRequest){
        return avisoService.reemplazar(id, avisoRequest);
    }

    /**
     * MODIFICADOR PARCIAL
     * PATCH /avisos/{id}
     *
     * @param id
     * @param avisoRequest
     * @return
     */
    @PatchMapping("/avisos/{id}")
    public Aviso modificarAviso(@PathVariable int id, @RequestBody AvisoRequest avisoRequest){
        return avisoService.reemplazoParcial(id, avisoRequest);
    }

    /**
     * BORRAR AVISO POR ID
     * DELETE /avisos/{id}
     *
     * @param id
     */
    @DeleteMapping("/avisos/{id}")
    public void eliminarAviso(@PathVariable int id){
        avisoService.borrar(id);
    }



}
