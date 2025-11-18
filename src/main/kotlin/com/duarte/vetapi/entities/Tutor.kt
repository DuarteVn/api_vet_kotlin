package com.duarte.vetapi.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "tutor")
data class Tutor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tutor")
    var id: Long = 0,

    @Column(name = "email", unique = true, nullable = false)
    var email : String = "",

    @Column(name = "tutor_name", nullable = false)
    var name : String = "",

    @Column(name = "password", nullable = false, length = 60)
    var password : String = "",

    @Column(name= "cpf", unique = true, nullable = false)
    var cpf : String = "",

    @Column(name = "create_time", nullable = false)
    var createTime : LocalDateTime = LocalDateTime.now(),

    )