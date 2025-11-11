package com.duarte.vetapi.controllers

import com.duarte.vetapi.entities.Especie
import com.duarte.vetapi.services.EspecieServiceInterface
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "Especies", description = "Operação com especies de animais.")
@RestController
@RequestMapping("/api/v1")
class EspecieController (private val especieService: EspecieServiceInterface){

    @Operation(summary = "Listar todas as espécies.")
    @GetMapping("/allespecies")
    fun getAllEspecies(): List<Especie> = especieService.getAllEspecies()

    @Operation(summary = "Listar espécies pelo ID")
    @GetMapping("/especie/{id}")
    fun getEspecieID(@PathVariable id_especie: Long): Especie? = especieService.getEspecieID(id_especie)

    @Operation(summary = "Cadastrar espécie de animal")
    @PostMapping("/cadastrarespecie")
    fun addEspecie(@RequestBody especie: Especie): Especie? = especieService.addEspecie(especie)


}