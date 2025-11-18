package com.duarte.vetapi.controllers

import com.duarte.vetapi.entities.Tutor
import com.duarte.vetapi.services.TutorServiceInterface
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*


@Tag(name="Tutores", description = "Operação com os tutores do animais.")
@RestController
@RequestMapping("/api")
class TutorController(private val tutorService: TutorServiceInterface) {

    @Operation(summary = "Listar tutores do animais")
    @GetMapping("/listartutores")
    fun listarTutores(): List<Tutor> = tutorService.buscarAllTutores()

    @Operation(summary = "Listar tutores do animais pelo ID")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Tutor encontrado"),
        ApiResponse(responseCode = "400", description = "Tutor não encontrado")
    ])
    @GetMapping("/tutor/{id}")
    fun listarTutorID(@PathVariable id: Long): Tutor = tutorService.buscarPorId(id)

    @Operation(summary = "Cadastrar tutores do animais")
    @PostMapping("/cadastrar")
    fun criarTutor(@RequestBody tutor: Tutor): Tutor = tutorService.criarTutor(tutor)
}
