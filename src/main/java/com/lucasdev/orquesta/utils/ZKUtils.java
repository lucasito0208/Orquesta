package com.lucasdev.orquesta.utils;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class ZKUtils {

    private final CuratorFramework cliente;

    public ZKUtils(CuratorFramework cliente) {
        this.cliente = cliente;
    }

    // Quiero hacer una lista de nodos a partir de una ruta
    public List<String> listarNodos(String path) throws Exception {
        return cliente.getChildren().forPath(path);
    }

    // leer datos de un nodo
    public String leerNodo(String path) throws Exception {
        byte[] datos = cliente.getData().forPath(path);
        return new String(datos, StandardCharsets.UTF_8);
    }

    // Verificar
    public boolean verificarNodo(String path) throws Exception {
        return cliente.checkExists().forPath(path) != null;
    }

    // Escuchar cambios temporales, en depuración y/o persistentes
    public void escucharNodo(String path) throws Exception {
        Watcher watcher = new Watcher() {

            @Override
            public void process(WatchedEvent evento) {
                if (evento.getType() == Event.EventType.NodeDataChanged) {
                    System.out.println("Evento cambiado en: " + evento.getPath());
                    try{
                        byte[] datos = cliente.getData().forPath(path);
                        String nuevo = new String(datos, StandardCharsets.UTF_8);
                        System.out.println("Cambio detectado -> "+path+":"+nuevo);
                        //Volvemos a registrar el Watcher
                        cliente.getData().usingWatcher(this).forPath(path);
                    }catch(Exception ex){
                        System.out.println("Error en la escucha del nodo en: "+path);
                        ex.printStackTrace();
                    }
                }
            }
        };

        try {
            cliente.getData().usingWatcher(watcher).forPath(path);
            System.out.println("Inicio de escucha persistente");
        } catch(Exception ex) {
            System.out.println("Error en la escucha del nodo en: "+path);
            ex.printStackTrace();
        }
    }

}
