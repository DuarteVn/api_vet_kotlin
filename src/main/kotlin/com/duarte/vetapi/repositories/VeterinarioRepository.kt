package com.duarte.vetapi.repositories

import com.duarte.vetapi.entities.Veterinario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface VeterinarioRepository : JpaRepository<Veterinario, Long> {


    fun findByCrmv(crmv: String): Optional<Veterinario>

    fun findByCpf(cpf: String): Optional<Veterinario>

}