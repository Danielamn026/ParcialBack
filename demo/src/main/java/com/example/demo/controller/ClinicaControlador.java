package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ClinicaDTO;
import com.example.demo.service.ClinicaServicio;

@RestController
@RequestMapping("/api/clinica")
public class ClinicaControlador {

    @Autowired
    private ClinicaServicio entidadServicio;

    @PostMapping()
    public ClinicaDTO crearClinica(@RequestBody ClinicaDTO clinicaDTO) {
        return entidadServicio.crearClinica(clinicaDTO);
    }

    @PutMapping()
    public ClinicaDTO actualizarClinica(@RequestBody ClinicaDTO clinicaDTO) {
        return entidadServicio.actualizarClinica(clinicaDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void eliminarClinica(@PathVariable Long id) {
        entidadServicio.eliminarClinica(id);
    }

    @GetMapping(value = "/{id}")
    public ClinicaDTO encontrarClinicaPorId(@PathVariable Long id) {
        return entidadServicio.encontrarClinicaDTOporId(id);
    }

    @GetMapping()
    public List<ClinicaDTO> encontrarTodasLasClinicas() {
        return entidadServicio.encontrarTodasLasClinicas();
    }
}
