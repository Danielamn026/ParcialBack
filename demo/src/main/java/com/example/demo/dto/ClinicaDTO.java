package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicaDTO {

    private Long id;
    private String nombre;
    private String direccion;
    private int cantidadCamas;
    private Long telefono;
    private String ciudad;
    private LocalDate fechaCreacion;
    private String eliminado;

    @JsonIgnore
    private List<DoctorDTO> doctores;

}