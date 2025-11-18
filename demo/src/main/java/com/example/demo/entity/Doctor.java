package com.example.demo.entity;

import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("deprecation")
@SQLDelete(sql = "UPDATE doctor SET eliminado = true WHERE id=?")
@Where(clause = "eliminado = false")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especialidad;
    private String email;
    private Long telefono;
    private LocalDate fecha_contratacion;

    private boolean eliminado = false;

    @ManyToOne
    @JoinColumn(name = "clinica_id")
    private Clinica clinica;

    public void setContratacion(Object fecha_contratacion) {
        throw new UnsupportedOperationException("Unimplemented method 'setFechaContratacion'");
    }
}
