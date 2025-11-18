package com.duarte.vetapi.repositories

import com.duarte.vetapi.entities.Consulta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.List

@Repository
interface ConsultaRepository : JpaRepository<Consulta, Long> {

    @Query("SELECT c FROM Consulta c WHERE c.animal.id = ?1")
    fun findAllByAnimalId(animalId: Long): List<Consulta>

    @Query("SELECT c FROM Consulta c WHERE c.veterinario.id = ?1")
    fun findAllByVeterinarioId(veterinarioId: Long): List<Consulta>
}