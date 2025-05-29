package com.lucasdev.orquesta.controller;

import com.lucasdev.orquesta.service.TareaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarea")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping("/ejecutar")
    public ResponseEntity<String> ejecutarTarea(@RequestParam String tarea) {
        return ResponseEntity.ok(tareaService.ejecutarTarea(tarea));
    }


}
