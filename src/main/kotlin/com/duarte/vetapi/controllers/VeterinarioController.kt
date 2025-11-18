package com.duarte.vetapi.controllers

import com.duarte.vetapi.entities.Veterinario
import com.duarte.vetapi.services.VeterinarioServiceInterface
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "Veterinario", description = "Operação com os Veterinarios.")
@RestController
@RequestMapping("/api")
class VeterinarioController(private val veterinarioService: VeterinarioServiceInterface) {


    @Operation(summary = "Listar Veterinarios")
    @GetMapping("/listarveterinarios")
    fun listarVeterinarios(): List<Veterinario> = veterinarioService.buscarAllVeterinario()

    @Operation(summary = "Listar veterinarios da clinica por ID")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Veterinario encontrado"),
        ApiResponse(responseCode = "400", description = "Veterinario não encontrado")
    ])
    @GetMapping("/veterinario/{id}")
    fun listarVeterinarioID(@PathVariable id: Long): Veterinario = veterinarioService.buscarVeterinarioPorId(id)


    @Operation(summary = "Cadastrar veterinario")
    @PostMapping("/cadastrovet")
    fun criarVeterinario(@RequestBody veterinario: Veterinario): Veterinario = veterinarioService.criarVeterinario(veterinario)



}