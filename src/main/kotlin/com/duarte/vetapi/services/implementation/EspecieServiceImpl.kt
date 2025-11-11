package com.duarte.vetapi.services.implementation

import com.duarte.vetapi.entities.Especie
import com.duarte.vetapi.repositories.EspecieRepository
import com.duarte.vetapi.services.EspecieServiceInterface
import org.springframework.stereotype.Service


@Service
class EspecieServiceImpl (
    private val especieRepository: EspecieRepository,

) : EspecieServiceInterface{

    override fun addEspecie(especie: Especie): Especie {
        return especieRepository.save(especie)
    }

    override fun getEspecieID(id_especie: Long): Especie? {
        return especieRepository.findById(id_especie).orElse(null)
    }

    override fun getAllEspecies(): List<Especie> {
        return especieRepository.findAll()
    }
}