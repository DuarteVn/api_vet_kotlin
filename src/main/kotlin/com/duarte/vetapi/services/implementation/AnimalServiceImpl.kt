package com.duarte.vetapi.services.implementation

import com.duarte.vetapi.entities.Animal
import com.duarte.vetapi.repositories.AnimalRepository
import com.duarte.vetapi.repositories.EspecieRepository
import com.duarte.vetapi.repositories.TutorRepository
import com.duarte.vetapi.services.AnimalServiceInterface
import org.springframework.stereotype.Service


@Service
class AnimalServiceImpl (
    private val animalRepository: AnimalRepository,
    private val tutorRepository: TutorRepository,
    private val especieRepository: EspecieRepository

    ) : AnimalServiceInterface{

    override fun criarAnimal(animal: Animal): Animal {
        val tutorId = animal.tutor.id ?: throw IllegalArgumentException("Tutor ID não pode ser nulo")
        val tutor = tutorRepository.findById(tutorId)
            .orElseThrow { Exception("Tutor com id $tutorId não encontrado.") }

        val especieId = animal.especie.id ?: throw IllegalArgumentException("Id_especie não pode ser nulo")
        val especie = especieRepository.findById(especieId)
            .orElseThrow { Exception("Especie com id $especieId não encontrada.") }

        val animalParaSalvar = animal.copy(
            especie = especie,
            tutor = tutor,
        )
        return animalRepository.save(animalParaSalvar)
    }

    override fun buscarAllAnimal(): List<Animal> {
        return animalRepository.findAll()
    }

    override fun buscarAnimalPorId(id: Long): Animal? {
        return animalRepository.findById(id).orElse(null)
    }

    override fun buscarPorTutor(tutorId: Long): List<Animal> {
        return animalRepository.findAllByTutorId(tutorId)
    }
}
