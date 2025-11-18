package com.duarte.vetapi.services.impl // (Confirme seu pacote)

import com.duarte.vetapi.entities.Consulta
import com.duarte.vetapi.repositories.AnimalRepository
import com.duarte.vetapi.repositories.ConsultaRepository
import com.duarte.vetapi.repositories.VeterinarioRepository
import com.duarte.vetapi.services.ConsultaServiceInterface
import org.springframework.stereotype.Service

@Service
class ConsultaServiceImpl(
    private val consultaRepository: ConsultaRepository,
    private val animalRepository: AnimalRepository,
    private val veterinarioRepository: VeterinarioRepository

) : ConsultaServiceInterface {

    override fun agendar(consulta: Consulta): Consulta {
        // Validar e Buscar o Animal
        val animalId = consulta.animal.id
            ?: throw IllegalArgumentException("ID do Animal é obrigatório")

        val animal = animalRepository.findById(animalId)
            .orElseThrow { IllegalArgumentException("Animal com ID $animalId não encontrado") }

        //Validar e Buscar o Veterinário
        val vetId = consulta.veterinario.id
            ?: throw IllegalArgumentException("ID do Veterinário é obrigatório")

        val veterinario = veterinarioRepository.findById(vetId)
            .orElseThrow { IllegalArgumentException("Veterinário com ID $vetId não encontrado") }

        val novaConsulta = consulta.copy(
            animal = animal,
            veterinario = veterinario
        )

        return consultaRepository.save(novaConsulta)
    }

    override fun buscarPorId(id: Long): Consulta? {
        return consultaRepository.findById(id).orElse(null)
    }

    override fun listarTodas(): List<Consulta> {
        return consultaRepository.findAll()
    }

    override fun buscarHistoricoAnimal(animalId: Long): List<Consulta> {
        return consultaRepository.findAllByAnimalId(animalId).toList()
    }

    override fun buscarAgendaVeterinario(veterinarioId: Long): List<Consulta> {
        return consultaRepository.findAllByVeterinarioId(veterinarioId).toList()
    }

    override fun remarcar(id: Long, dadosNovos: Consulta): Consulta {
        val consultaExistente = consultaRepository.findById(id)
            .orElseThrow { RuntimeException("Consulta não encontrada") }

        val consultaAtualizada = consultaExistente.copy(
            dataHoraConsulta = dadosNovos.dataHoraConsulta
        )
        return consultaRepository.save(consultaAtualizada)
    }

    override fun cancelar(id: Long) {
        if (!consultaRepository.existsById(id)) {
            throw RuntimeException("Consulta não encontrada")
        }
        consultaRepository.deleteById(id)
    }
}