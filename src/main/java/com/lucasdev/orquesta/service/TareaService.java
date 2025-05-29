package com.lucasdev.orquesta.service;

import com.lucasdev.orquesta.logic.TareaLogica;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
public class TareaService {

    private final ObjectProvider<TareaLogica> logica;

    public TareaService(ObjectProvider<TareaLogica> logica) {
        this.logica = logica;
    }

    public String ejecutarTarea(String nombreTarea) {
        TareaLogica tarea = logica.getObject();
        tarea.prepararTarea(nombreTarea);
        return tarea.ejecutar();
    }
}
