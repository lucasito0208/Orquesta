package com.lucasdev.orquesta.logic;

import org.springframework.stereotype.Component;

@Component
public class TareaLogica {

    private String estado;

    public void prepararTarea(String nombreTarea) {
        this.estado = "Preparando tarea..."+nombreTarea;
    }

    public String ejecutar() {
        return "Ejecutando → " + estado + " con lógica aislada";
    }
}
