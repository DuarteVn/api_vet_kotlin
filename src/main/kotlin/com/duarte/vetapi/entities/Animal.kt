package com.duarte.vetapi.entities

import jakarta.persistence.*
import java.sql.Date
import java.time.LocalDateTime

@Entity
@Table(name = "animal")
data class Animal(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_animal")
    var id_animal : Long = 0,

    @Column(name = "nome", nullable = false, length = 50)
    var nome: String,

    @Column(name = "raca")
    var raca: String,

    @Column(name = "genero", length = 10)
    var genero: String,

    @Column(name = "nascimento")
    var nascimento: Date,

    @Column(name = "peso")
    var peso: Double,

    @Column(name = "create_time", nullable = false, updatable = false)
    var create_time : LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "tutor_id_tutor", nullable = false)
    val tutor : Tutor,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especie_id_especie", nullable = false)
    val especie: Especie
)