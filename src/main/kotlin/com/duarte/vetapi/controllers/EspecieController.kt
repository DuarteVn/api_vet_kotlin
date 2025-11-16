package com.duarte.vetapi.controllers

import com.duarte.vetapi.entities.Especie
import com.duarte.vetapi.services.EspecieServiceInterface
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Especies", description = "Operação com especies de animais.")
@RestController
@RequestMapping("/api/v1")
class EspecieController (private val especieService: EspecieServiceInterface){

    @Operation(summary = "Listar todas as espécies.")
    @GetMapping("/allespecies")
    fun getAllEspecies(): List<Especie> = especieService.getAllEspecies()

    @Operation(summary = "Listar espécies pelo ID")
    @GetMapping("/especie/{id_especie}")
    fun getEspecieID(@PathVariable id_especie: Long): ResponseEntity<Especie> {
        val especie = especieService.getEspecieID(id_especie)

        return if (especie !=null) {
            ResponseEntity.ok(especie)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @Operation(summary = "Cadastrar espécie de animal")
    @PostMapping("/cadastrarespecie")
    fun addEspecie(@RequestBody especie: Especie): Especie? = especieService.addEspecie(especie)


}