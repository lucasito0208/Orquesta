package com.lucasdev.orquesta.repositorio;

import com.lucasdev.orquesta.domain.Servicio;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepositorio extends Neo4jRepository<Servicio, String> {
}
