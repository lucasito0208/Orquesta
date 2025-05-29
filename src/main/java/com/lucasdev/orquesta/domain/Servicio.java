package com.lucasdev.orquesta.domain;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node
@Data
public class Servicio {

    private String nombre;

    private String tipo;

    @Relationship(type = "ORQUESTA")
    private Set<Tarea> tareas;

    @Relationship(type = "DEPENDE_DE", direction = Relationship.Direction.OUTGOING)
    private Set<Servicio> dependencias;

    public Servicio() {
    }

    public Servicio(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }
}
