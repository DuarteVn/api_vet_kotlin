package com.duarte.vetapi.controllers

import com.duarte.vetapi.entities.Veterinario
import com.duarte.vetapi.services.VeterinarioServiceInterface
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Veterinario", description = "Operação com os Veterinarios.")
@RestController
@RequestMapping("/api/mv/")
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

    @Operation(summary = "Atualizar dados de um veterinário")
    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody veterinario: Veterinario): ResponseEntity<Veterinario> {
        return try {
            val atualizado = veterinarioService.atualizar(id, veterinario)
            ResponseEntity.ok(atualizado)
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }

    @Operation(summary = "Deletar um veterinário")
    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            veterinarioService.deletar(id)
            ResponseEntity.noContent().build()
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }


    @Operation(summary = "Buscar veterinários por nome (parcial)")
    @GetMapping("/busca") // Ex: /api/veterinarios/busca?nome=Ana
    fun buscarPorNome(@RequestParam nome: String): ResponseEntity<List<Veterinario>> {
        val veterinarios = veterinarioService.buscarPorNome(nome)
        return ResponseEntity.ok(veterinarios)
    }


    @Operation(summary = "Buscar veterinário pelo CRMV")
    @GetMapping("/crmv/{crmv}")
    fun buscarPorCrmv(@PathVariable crmv: String): ResponseEntity<Veterinario> {
        val veterinario = veterinarioService.buscarPorCrmv(crmv)
        return if (veterinario != null) {
            ResponseEntity.ok(veterinario)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}