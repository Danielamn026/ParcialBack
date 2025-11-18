package com.example.demo.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private Long id;

    private String nombre;
    private String especialidad;
    private String email;
    private Long telefono;
    private LocalDate fechaContratacion;
    private boolean eliminado = false;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long ClinicaId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ClinicaDTO clinica;

}
