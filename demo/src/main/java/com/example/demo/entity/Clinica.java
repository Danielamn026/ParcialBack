package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@SQLDelete(sql = "UPDATE clinica SET eliminado = true WHERE id=?")
@Where(clause = "eliminado = false")
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;
    private int cantidad_camas;
    private Long telefono;
    private String ciudad;
    private LocalDate fecha_creacion;

    private boolean eliminado = false;

    @OneToMany(mappedBy = "clinica")
    private List<Doctor> doctores;
}
