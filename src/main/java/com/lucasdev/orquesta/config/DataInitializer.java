package com.lucasdev.orquesta.config;

import com.lucasdev.orquesta.repositorio.ServicioRepositorio;
import com.lucasdev.orquesta.repositorio.TareaRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ServicioRepositorio servicioRepositorio;
    private final TareaRepositorio tareaRepositorio;

    public DataInitializer(ServicioRepositorio servicioRepositorio, TareaRepositorio tareaRepositorio) {
        this.servicioRepositorio = servicioRepositorio;
        this.tareaRepositorio = tareaRepositorio;
    }

    @Override
    public void run(String... args) throws Exception {

    }


}
