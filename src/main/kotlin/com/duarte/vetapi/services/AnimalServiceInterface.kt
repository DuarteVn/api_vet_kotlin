package com.duarte.vetapi.services

import com.duarte.vetapi.entities.Animal

import org.springframework.stereotype.Service

@Service
interface AnimalServiceInterface {
    fun criarAnimal(animal: Animal): Animal
    fun buscarAnimalPorId(id: Long): Animal?
    fun buscarAllAnimal(): List<Animal>
    fun buscarPorTutor(tutorId: Long): List<Animal>
}