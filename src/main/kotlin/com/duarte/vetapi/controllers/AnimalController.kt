package com.duarte.vetapi.controllers

import com.duarte.vetapi.entities.Animal
import com.duarte.vetapi.services.implementation.AnimalServiceImpl
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Tag(name="Animais", description = "Operação de cadastro e consulta de animais.")
@RestController
@RequestMapping("/api/animais")
class AnimaisController (
    private val animalServiceImpl: AnimalServiceImpl
) {
    @Operation(summary = "Cadastrar um novo animal")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Cadastrado com sucesso"),])
    @PostMapping
    fun criaAnimal(@RequestBody @Valid animal: Animal): ResponseEntity<Animal>{
        return try {
            val animalSalvo = animalServiceImpl.criarAnimal(animal)
            ResponseEntity.status(HttpStatus.CREATED).body(animalSalvo)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }
}