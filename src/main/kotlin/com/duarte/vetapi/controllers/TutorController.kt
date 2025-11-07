package com.duarte.vetapi.controllers

import com.duarte.vetapi.entities.Tutor
import com.duarte.vetapi.services.TutorServiceInterface
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name="Tutor Controller", description = "Operação com os tutores do animais.")
@RestController
@RequestMapping("/api")
class TutorController(private val tutorService: TutorServiceInterface) {

    @Operation(summary = "Listar tutores do animais")
    @GetMapping("/listartutores")
    fun listarTutores(): List<Tutor> = tutorService.buscarAllTutores()


}
