package com.duarte.vetapi.repositories

import com.duarte.vetapi.entities.Especie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EspecieRepository : JpaRepository<Especie, Long> {}