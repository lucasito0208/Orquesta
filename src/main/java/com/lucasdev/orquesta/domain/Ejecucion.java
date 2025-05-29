package com.lucasdev.orquesta.domain;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDateTime;

@Node
public class Ejecucion {

    @Id
    @GeneratedValue
    public Long id;

    public String resultado;

    public LocalDateTime timestamp;

    @Relationship(type = "EJECUTADA_EN", direction = Relationship.Direction.INCOMING)
    public Tarea tarea;

    @Relationship(type = "EJECUTA", direction = Relationship.Direction.INCOMING)
    public Servicio servicio;

    public Ejecucion() {}

    public Ejecucion(String resultado, LocalDateTime timestamp, Tarea tarea, Servicio servicio) {
        this.resultado = resultado;
        this.timestamp = timestamp;
        this.tarea = tarea;
        this.servicio = servicio;
    }
}
