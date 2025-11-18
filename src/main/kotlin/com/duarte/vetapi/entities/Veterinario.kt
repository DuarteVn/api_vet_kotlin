package com.duarte.vetapi.entities

import jakarta.persistence.*
import java.time.LocalDate


@Entity
@Table(name = "veterinario")
data class Veterinario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_veterinario")
    var id : Long = 0,

    @Column(name = "nome", nullable = false, length = 255)
    var nome: String,

    @Column(name = "cpf", nullable = false, length = 32, unique = true)
    var cpf: String,

    @Column(name = "nascimento")
    var nascimento: LocalDate? = null,

    @Column(name = "crmv")
    var crmv: String,

    @Column(name = "endereco", length = 255)
    var endereco : String,

    @Column(name = "telefone")
    var telefone : String,

    )