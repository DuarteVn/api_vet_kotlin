package com.duarte.vetapi.services.implementation

import com.duarte.vetapi.entities.Tutor
import com.duarte.vetapi.repositories.TutorRepository
import com.duarte.vetapi.services.TutorServiceInterface
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.logging.Logger
import org.slf4j.LoggerFactory

@Service
class TutorServiceImpl(
        private val tutorRepository: TutorRepository,
        private val passwordEncoder: PasswordEncoder

) : TutorServiceInterface {

    override fun criarTutor(tutor: Tutor): Tutor{
        val tutorParaSalvar = tutor.copy(
            password = passwordEncoder.encode(tutor.password)
            )
        return tutorRepository.save(tutorParaSalvar)
    }

    override fun buscarPorId(id: Long): Tutor?{
        return tutorRepository.findById(id).orElse(null)
    }

    override fun buscarAllTutores(): List<Tutor> {
        return tutorRepository.findAll()

    }
}

