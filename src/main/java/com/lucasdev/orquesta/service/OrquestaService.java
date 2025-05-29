package com.lucasdev.orquesta.service;

import jakarta.annotation.PostConstruct;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class OrquestaService {

    private final CuratorFramework cliente;
    private final String nodoId = UUID.randomUUID().toString();
    private static final String NODO_PATH = "/nodos";
    private static final String LIDER_PATH = "/lider";

    public OrquestaService(CuratorFramework cliente) throws Exception {
        this.cliente = cliente;

        // Asegura que existen los nodos base
        if (cliente.checkExists().forPath(NODO_PATH) == null) {
            cliente.create().creatingParentsIfNeeded().forPath(NODO_PATH);
        }

        // Registra el nodo actual como efímero (se borra si la app cae)
        cliente.create()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(NODO_PATH + "/" + nodoId, "activo".getBytes());

        System.out.println("🟢 Nodo registrado: " + nodoId);

        iniciarEleccionDeLider();
    }


    public void iniciarEleccionDeLider() {
        LeaderSelector selector = new LeaderSelector(cliente, LIDER_PATH, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) {
                System.out.println("Soy el LÍDER del clúster: " + nodoId);
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    private void escucharLider() {
        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent evento) {
                if (evento.getType() == Event.EventType.NodeDataChanged) {
                    try {
                        byte[] datos = cliente.getData().forPath(LIDER_PATH);
                        String nuevoValor = new String(datos, StandardCharsets.UTF_8);
                        System.out.println("Nuevo líder detectado: " + nuevoValor);

                        // Re-registro para futuras escuchas
                        cliente.getData().usingWatcher(this).forPath(LIDER_PATH);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        try {
            cliente.getData().usingWatcher(watcher).forPath(LIDER_PATH);
            System.out.println("Escuchando cambios en /lider");
        } catch (Exception e) {
            System.err.println("Error al iniciar la escucha de /lider");
            e.printStackTrace();
        }
    }

    // Se ejecutará una vez se hayan inyectado las dependencias
    @PostConstruct
    public void iniciar() {
        iniciarEleccionDeLider();
        escucharLider();
    }



}
