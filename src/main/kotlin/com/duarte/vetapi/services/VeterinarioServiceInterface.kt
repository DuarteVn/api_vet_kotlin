package com.duarte.vetapi.services

import com.duarte.vetapi.entities.Veterinario

import org.springframework.stereotype.Service

@Service
interface VeterinarioServiceInterface {
    fun criarVeterinario(veterinario: Veterinario): Veterinario
    fun buscarVeterinarioPorId(id: Long): Veterinario
    fun buscarAllVeterinario(): List<Veterinario>
    fun atualizar(id: Long, veterinario: Veterinario): Veterinario
    fun deletar(id: Long)

    fun buscarPorNome(parteDoNome: String): List<Veterinario>
    fun buscarPorCrmv(crmv: String): Veterinario?

}