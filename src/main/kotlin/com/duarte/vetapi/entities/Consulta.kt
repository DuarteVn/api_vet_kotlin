package com.duarte.vetapi.entities

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "consulta")
data class Consulta(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    val id: Long? = null,

    @Column(name = "data_hora_consulta", nullable = false)
    val dataHoraConsulta: LocalDateTime,

    @Column(name = "create_time", nullable = false, updatable = false)
    val createTime: LocalDateTime = LocalDateTime.now(),

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id_animal", nullable = false)
    val animal: Animal,

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veterinario_id_veterinario", nullable = false)
    val veterinario: Veterinario
)