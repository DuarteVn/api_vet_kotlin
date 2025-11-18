package com.duarte.vetapi.controllers

import com.duarte.vetapi.entities.Consulta
import com.duarte.vetapi.services.ConsultaServiceInterface
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Consultas", description = "Agendamento e histórico de consultas.")
@RestController
@RequestMapping("/api/consultas")
class ConsultaController(
    private val consultaService: ConsultaServiceInterface
) {

    @Operation(summary = "Agendar uma nova consulta")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Consulta agendada com sucesso"),
        ApiResponse(responseCode = "400", description = "Erro: Animal ou Veterinário não encontrados")
    ])
    @PostMapping
    fun agendar(@RequestBody consulta: Consulta): ResponseEntity<Any> {
        return try {
            val novaConsulta = consultaService.agendar(consulta)
            ResponseEntity.status(HttpStatus.CREATED).body(novaConsulta)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(mapOf("erro" to e.message))
        }
    }

    @Operation(summary = "Detalhes de uma consulta")
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Consulta> {
        val consulta = consultaService.buscarPorId(id)
        return if (consulta != null) {
            ResponseEntity.ok(consulta)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @Operation(summary = "Histórico de consultas de um animal")
    @GetMapping("/animal/{animalId}")
    fun historicoAnimal(@PathVariable animalId: Long): ResponseEntity<List<Consulta>> {
        val historico = consultaService.buscarHistoricoAnimal(animalId)
        return ResponseEntity.ok(historico)
    }

    @Operation(summary = "Agenda de consultas de um veterinário")
    @GetMapping("/veterinario/{vetId}")
    fun agendaVeterinario(@PathVariable vetId: Long): ResponseEntity<List<Consulta>> {
        val agenda = consultaService.buscarAgendaVeterinario(vetId)
        return ResponseEntity.ok(agenda)
    }

    @Operation(summary = "Remarcar data de uma consulta")
    @PutMapping("/{id}")
    fun remarcar(@PathVariable id: Long, @RequestBody consulta: Consulta): ResponseEntity<Consulta> {
        return try {
            val atualizada = consultaService.remarcar(id, consulta)
            ResponseEntity.ok(atualizada)
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }

    @Operation(summary = "Cancelar (deletar) uma consulta")
    @DeleteMapping("/{id}")
    fun cancelar(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            consultaService.cancelar(id)
            ResponseEntity.noContent().build()
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }
}