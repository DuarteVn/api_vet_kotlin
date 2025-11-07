package com.duarte.vetapi.repositories

import com.duarte.vetapi.entities.Tutor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TutorRepository : JpaRepository<Tutor, Long> {}

// Ao chamar JpaRepository a interface ganha INSTANTANEAMENTE
// os métodos prontos (save, findById e etc...)