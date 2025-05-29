# 🧩 Orquesta Neo4j

Este proyecto simula un sistema distribuido de orquestación de tareas, donde múltiples servicios gestionan y ejecutan tareas interrelacionadas, empleando tecnologías modernas como Spring Boot, Neo4j y Apache Zookeeper.

## 🚀 Tecnologías principales

- **Java 17**
- **Spring Boot 3**
- **Neo4j** (como base de datos de grafos)
- **Spring Data Neo4j**
- **Apache Zookeeper + Curator** (coordinación de nodos y elección de líder)
- **MapStruct** (mapeo entre entidades y DTOs)
- **RabbitMQ** (próxima integración para mensajería)

## 🎯 Objetivo del proyecto

- Simular una arquitectura de microservicios orquestados mediante nodos coordinados.
- Usar Neo4j para representar gráficamente relaciones `SERVICIO` ↔ `TAREA`.
- Implementar lógica de elección de líder con Zookeeper.
- Establecer buenas prácticas de inicialización de datos, escucha de eventos y comunicación entre nodos.


## ⚙️ Inicialización

Los datos se inicializan automáticamente con la clase `DataInitializer` al arrancar la app, insertando un servicio de ejemplo con tareas relacionadas, siempre que la base de datos esté vacía.

## 🔍 Consulta de nodos (en Neo4j Browser)

```cypher
MATCH (s:Servicio)-[:EJECUTADA_POR]->(t:Tarea)
RETURN s, t;


