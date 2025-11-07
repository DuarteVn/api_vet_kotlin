package com.duarte.vetapi.services

import com.duarte.vetapi.entities.Tutor


interface TutorServiceInterface {
    fun criarTutor(tutor: Tutor): Tutor
    fun buscarPorId(id: Long): Tutor?
    fun buscarAllTutores(): List<Tutor>
}