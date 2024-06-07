package com.evalueytor.empresa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalueytor.empresa.models.Estado;
import com.evalueytor.empresa.repositories.EstadoRepository;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {
    @Autowired
    EstadoRepository estadoRepository;

   

    // Listar todo
    @GetMapping("/findall")
    public List<Estado> listarEstado() {
        return estadoRepository.findAll();
    }


    // Listar por Id
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Estado> obtenerEmpresaPorId(@PathVariable Long id) {
        Optional<Estado> estadoOptional = estadoRepository.findById(id);
        return estadoOptional.map(premio -> new ResponseEntity<>(premio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear una nueva empresa
    @PostMapping("/save")
    public ResponseEntity<Estado> crearEstado(@RequestBody Estado nuevoEstado) {
        Estado estadoGuardado = estadoRepository.save(nuevoEstado);
        return new ResponseEntity<>(estadoGuardado, HttpStatus.CREATED);
    }

    // Actualizar empresa
    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<Estado> actualizarEstado(@PathVariable Long id, @RequestBody Estado estadoActual) {
        Optional<Estado> estadoOptional = estadoRepository.findById(id);
        return estadoOptional.map(estado -> {
            estado.setId(id);
            estado.setNombre(estadoActual.getNombre());
            
            Estado estadoActualGuardado = estadoRepository.save(estado);
            return new ResponseEntity<>(estadoActualGuardado, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar un empresa por ID
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Void> eliminarEstado(@PathVariable Long id) {
        Optional<Estado> estadoOptional = estadoRepository.findById(id);
        if (estadoOptional.isPresent()) {
            estadoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

   
}

