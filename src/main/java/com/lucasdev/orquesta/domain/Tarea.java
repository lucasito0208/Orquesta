package com.lucasdev.orquesta.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String descripcion;

    @Relationship(type = "EJECUTADA_POR", direction = Relationship.Direction.OUTGOING)
    private Servicio ejecutor;

}
