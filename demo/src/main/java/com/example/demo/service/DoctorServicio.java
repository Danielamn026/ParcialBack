package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Clinica;
import com.example.demo.repository.DoctorRepositorio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DoctorServicio {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DoctorRepositorio doctorRepositorio;

    @Autowired
    private ClinicaServicio clinicaServicio;

    public DoctorDTO crearDoctor(DoctorDTO doctorDTO) {

        Clinica clinica = clinicaServicio.encontrarClinicaPorId(doctorDTO.getClinicaId());
        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
        doctor.setClinica(clinica);
        Doctor doctorGuardado = doctorRepositorio.save(doctor);
        return modelMapper.map(doctorGuardado, DoctorDTO.class);
    }

    public DoctorDTO actualizarDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = doctorRepositorio.findById(doctorDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor " + doctorDTO.getId() + " no encontrado"));

        Clinica clinica = clinicaServicio.encontrarClinicaPorId(doctorDTO.getClinicaId());

        doctor.setNombre(doctorDTO.getNombre());
        doctor.setEspecialidad(doctorDTO.getEspecialidad());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setTelefono(doctorDTO.getTelefono());
        doctor.setFecha_contratacion(doctorDTO.getFechaContratacion());
        doctor.setClinica(clinica);

        Doctor doctorActualizado = doctorRepositorio.save(doctor);

        return modelMapper.map(doctorActualizado, DoctorDTO.class);

    }

    public DoctorDTO encontrarDoctorPorId(Long id) {
        Doctor doctor = doctorRepositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor " + id + " no encontrado"));

        return modelMapper.map(doctor, DoctorDTO.class);
    }

    public void eliminarDoctor(Long id) {
        Doctor doctor = doctorRepositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor " + id + " no encontrado"));

        doctor.setEliminado(true);
        doctorRepositorio.save(doctor);
    }

    public List<DoctorDTO> encontrarTodosLosDoctores() {
        return doctorRepositorio.findAll().stream()
                .filter(c -> !c.isEliminado())
                .map(c -> modelMapper.map(c, DoctorDTO.class))
                .toList();
    }
}
