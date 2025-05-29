package com.lucasdev.orquesta.factory;

import com.lucasdev.orquesta.domain.Servicio;
import com.lucasdev.orquesta.domain.Tarea;
import org.springframework.stereotype.Component;

@Component
public class TareaFactory {

    public Tarea crear(String nombre, String descripcion, Servicio ejecutor) {
        return Tarea.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .ejecutor(ejecutor)
                .build();
    }
}
