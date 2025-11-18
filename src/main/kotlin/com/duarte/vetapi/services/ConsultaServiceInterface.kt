package com.duarte.vetapi.services

import com.duarte.vetapi.entities.Consulta
import org.springframework.stereotype.Service

@Service
interface ConsultaServiceInterface {

    fun agendar(consulta: Consulta): Consulta

    fun buscarPorId(id: Long): Consulta?

    fun listarTodas(): List<Consulta>

    fun buscarHistoricoAnimal(animalId: Long): List<Consulta>
    fun buscarAgendaVeterinario(veterinarioId: Long): List<Consulta>
    fun remarcar(id: Long, novaData: Consulta): Consulta
    fun cancelar(id: Long)
}