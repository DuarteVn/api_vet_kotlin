package com.duarte.vetapi.services.implementation

import com.duarte.vetapi.entities.Tutor
import com.duarte.vetapi.repositories.TutorRepository
import com.duarte.vetapi.services.TutorServiceInterface
import org.springframework.stereotype.Service

@Service
class TutorServiceImpl(
        private val tutorRepository: TutorRepository,


) : TutorServiceInterface {

    override fun criarTutor(tutor: Tutor): Tutor{

        return tutorRepository.save(tutor)
    }

    override fun buscarPorId(id: Long): Tutor{
        return tutorRepository.findById(id).orElse(null)
    }

    override fun buscarAllTutores(): List<Tutor> {
        return tutorRepository.findAll()

    }
}

