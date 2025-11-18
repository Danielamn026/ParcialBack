package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.service.DoctorServicio;

@RestController
@RequestMapping("/api/doctor")
public class DoctorControlador {

    @Autowired
    private DoctorServicio doctorServicio;

    @PostMapping()
    public DoctorDTO crearDoctor(@RequestBody DoctorDTO doctorDTO) {
        return doctorServicio.crearDoctor(doctorDTO);
    }

    @PutMapping()
    public DoctorDTO actualizarDoctor(@RequestBody DoctorDTO doctorDTO) {
        return doctorServicio.actualizarDoctor(doctorDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void eliminarDoctor(@PathVariable Long id) {
        doctorServicio.eliminarDoctor(id);
    }

    @GetMapping(value = "/{id}")
    public DoctorDTO encontrarDoctorPorId(@PathVariable Long id) {
        return doctorServicio.encontrarDoctorPorId(id);
    }

    @GetMapping()
    public List<DoctorDTO> encontrarTodosLosDoctores() {
        return doctorServicio.encontrarTodosLosDoctores();
    }
}
