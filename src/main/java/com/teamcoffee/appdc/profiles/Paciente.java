package com.teamcoffee.appdc.profiles;

import com.teamcoffee.appdc.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPatient;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private User user;

    @Column(name = "nombre", length = 50, nullable = false)
    private String username;

    @Column(name = "apellido", length = 50, nullable = false)
    private String lastname;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate dateBirth;

    @Column(name = "direccion", length = 150, nullable = false)
    private String address;

    @Column(name = "numero_telefono", length = 15, nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private TypeDiabetes typeDiabetes;

}
