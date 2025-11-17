package com.duarte.vetapi.controllers

import com.duarte.vetapi.entities.Animal
import com.duarte.vetapi.services.AnimalServiceInterface
import com.duarte.vetapi.services.implementation.AnimalServiceImpl
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Tag(name="Animais", description = "Operação de cadastro e consulta de animais.")
@RestController
@RequestMapping("/api/animais")
class AnimaisController (
    private val animalService: AnimalServiceInterface
) {
    @Operation(summary = "Cadastrar um novo animal")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Cadastrado com sucesso"),])
    @PostMapping
    fun criaAnimal(@RequestBody @Valid animal: Animal): ResponseEntity<Animal>{
        return try {
            val animalSalvo = animalService.criarAnimal(animal)
            ResponseEntity.status(HttpStatus.CREATED).body(animalSalvo)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @Operation(summary = "Lista todos os animais cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de animais")
    @GetMapping
    fun listarTodosAnimais(): ResponseEntity<List<Animal>> {
        val animais = animalService.buscarAllAnimal()
        return ResponseEntity.ok(animais)
    }


    @Operation(summary = "Busca um animal pelo seu ID")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Animal encontrado"),
        ApiResponse(responseCode = "404", description = "Animal não encontrado")
    ])
    @GetMapping("/{id}")
    fun buscarAnimalPorId(@PathVariable id: Long): ResponseEntity<Animal> {
        val animal = animalService.buscarAnimalPorId(id)
        return if (animal != null) {
            ResponseEntity.ok(animal)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @Operation(summary = "Lista todos os animais de um tutor específico")
    @ApiResponse(responseCode = "200", description = "Lista de animais do tutor")
    @GetMapping("/tutor/{tutorId}")
    fun buscarAnimaisPorTutor(@PathVariable tutorId: Long): ResponseEntity<List<Animal>> {
        val animais = animalService.buscarPorTutor(tutorId)
        return ResponseEntity.ok(animais)
    }

}