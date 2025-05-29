package com.lucasdev.orquesta.repositorio;

import com.lucasdev.orquesta.domain.Tarea;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepositorio extends Neo4jRepository<Tarea, Long> {
}
