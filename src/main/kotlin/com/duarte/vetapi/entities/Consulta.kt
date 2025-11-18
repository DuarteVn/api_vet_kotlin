/*package com.duarte.vetapi.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.sql.Date
import java.time.LocalDateTime

@Entity
@Table(name = "consulta")
data class Consulta (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_consulta")
    var id: Long? = null,

    @Column(name = "especie",unique = true, nullable = false)
    var name : String,

    @Column(name = "create_time", nullable = false, updatable = false)
    var create_time : LocalDateTime = LocalDateTime.now(),

    @Column(name = "create_time", nullable = false, updatable = false)
    var create_time : LocalDateTime = LocalDateTime.now(),

    @Column(name = "create_time", nullable = false, updatable = false)
    var create_time : LocalDateTime = LocalDateTime.now(),

    )*/