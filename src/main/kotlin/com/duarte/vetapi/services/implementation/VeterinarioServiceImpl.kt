package com.duarte.vetapi.services.implementation

import org.springframework.stereotype.Service
import com.duarte.vetapi.repositories.VeterinarioRepository
import com.duarte.vetapi.services.VeterinarioServiceInterface
import com.duarte.vetapi.entities.Veterinario

@Service
class VeterinarioServiceImpl(
    private val veterinarioRepository: VeterinarioRepository,

    ): VeterinarioServiceInterface{

    override fun criarVeterinario(veterinario: Veterinario): Veterinario {
        val cpfExistente = veterinarioRepository.findByCpf(veterinario.cpf)
        val crmvExistente = veterinarioRepository.findByCrmv(veterinario.crmv)

        if (cpfExistente.isPresent){
            throw Exception("Veterinario ja existente com esse CPF")
        }

        else if (crmvExistente.isPresent){
            throw Exception("Veterinario ja existente com esse CRMV")
        }

        return veterinarioRepository.save(veterinario)
    }

    override fun buscarVeterinarioPorId(id: Long): Veterinario {
        return veterinarioRepository.findById(id).orElse(null)
    }

    override fun buscarAllVeterinario(): List<Veterinario> {
        return veterinarioRepository.findAll()
    }

}
