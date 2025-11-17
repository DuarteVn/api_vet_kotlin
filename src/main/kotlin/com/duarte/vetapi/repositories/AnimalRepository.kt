package com.duarte.vetapi.repositories

import com.duarte.vetapi.entities.Animal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AnimalRepository : JpaRepository<Animal, Long>{

    @Query("SELECT a FROM Animal a WHERE a.tutor.id = ?1")
    fun findAllByTutorId(tutorId: Long): List<Animal>

    @Query("SELECT a FROM Animal a WHERE a.especie.id = ?1")
    fun findAllByEspecieId(especieId: Long): List<Animal>

}