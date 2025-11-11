package com.duarte.vetapi.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "especie")
data class Especie (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_especie")
    var id_especie: Long = 0,

    @Column(name = "especie",unique = true, nullable = false)
    var name : String,

)
