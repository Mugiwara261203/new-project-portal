package com.teamcoffee.appdc.profiles;

import com.teamcoffee.appdc.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medicos")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDoctor;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private User user;

    @Column(name = "nombre", length = 50, nullable = false)
    private String username;

    @Column(name = "apellido", length = 50, nullable = false)
    private String lastname;

    @Column(name = "especializacion", length = 50, nullable = false)
    private String specialization;

    @Column(name = "numero_telefono", length = 15, nullable = false)
    private String phoneNumber;
}
