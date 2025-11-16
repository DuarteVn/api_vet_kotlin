package com.duarte.vetapi.repositories

import com.duarte.vetapi.entities.Animal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnimalRepository : JpaRepository<Animal, Long>{

    fun findAllByTutorId(tutorId: Long): List<Animal>

    fun findAllByEspecieId(especieId: Long): List<Animal>

}