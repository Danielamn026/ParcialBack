package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.ClinicaDTO;
import com.example.demo.entity.Clinica;
import com.example.demo.repository.ClinicaRepositorio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClinicaServicio {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClinicaRepositorio clinicaRepositorio;

    public ClinicaDTO crearClinica(ClinicaDTO clinicaDTO) {

        Clinica clinica = modelMapper.map(clinicaDTO, Clinica.class);
        Clinica clinicaGuardada = clinicaRepositorio.save(clinica);

        return modelMapper.map(clinicaGuardada, ClinicaDTO.class);
    }

    public ClinicaDTO actualizarClinica(ClinicaDTO clinicaDTO) {
        Clinica clinica = clinicaRepositorio.findById(clinicaDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Clinica no encontrada"));

        clinica.setNombre(clinicaDTO.getNombre());
        clinica.setDireccion(clinicaDTO.getDireccion());
        clinica.setTelefono(clinicaDTO.getTelefono());
        clinica.setCiudad(clinicaDTO.getCiudad());
        clinica.setFecha_creacion(clinicaDTO.getFechaCreacion());
        clinica.setCantidad_camas(clinicaDTO.getCantidadCamas());

        Clinica clinicaActualizada = clinicaRepositorio.save(clinica);
        return modelMapper.map(clinicaActualizada, ClinicaDTO.class);
    }

    public ClinicaDTO encontrarClinicaDTOporId(Long id) {
        Clinica clinica = clinicaRepositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clinica " + id + " no encontrada"));

        return modelMapper.map(clinica, ClinicaDTO.class);
    }

    public Clinica encontrarClinicaPorId(Long id) {
        return clinicaRepositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clinica " + id + " no encontrada"));
    }

    public void eliminarClinica(Long id) {
        Clinica clinica = clinicaRepositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clinica " + id + " no encontrada"));

        clinica.setEliminado(true);
        clinicaRepositorio.save(clinica);
    }

    public List<ClinicaDTO> encontrarTodasLasClinicas() {
        List<Clinica> clinicas = clinicaRepositorio.findAll();
        return clinicas.stream()
                .filter(e -> !e.isEliminado())
                .map(entidad -> modelMapper.map(entidad, ClinicaDTO.class))
                .toList();
    }
}
