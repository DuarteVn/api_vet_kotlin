package com.duarte.vetapi.services

import com.duarte.vetapi.entities.Especie
import org.springframework.stereotype.Service

@Service
interface EspecieServiceInterface {
    fun addEspecie(especie: Especie): Especie
    fun getEspecieID(id: Long): Especie?
    fun getAllEspecies(): List<Especie>

}