package com.example.clienteservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "C|P", message = "El tipo de documento debe ser 'C' para cédula o 'P' para pasaporte")
    private String tipoDocumento;

    @Size(min = 5, max = 15, message = "El número de documento debe tener entre 5 y 15 caracteres")
    private String numeroDocumento;

    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;
    private String direccion;
    private String ciudadResidencia;

    @Email(message = "El correo debe ser válido")
    private String correoElectronico;

}
