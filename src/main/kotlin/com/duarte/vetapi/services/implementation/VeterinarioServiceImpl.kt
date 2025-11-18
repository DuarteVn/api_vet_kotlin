package com.duarte.vetapi.services.implementation

import org.springframework.stereotype.Service
import com.duarte.vetapi.repositories.VeterinarioRepository
import com.duarte.vetapi.services.VeterinarioServiceInterface
import com.duarte.vetapi.entities.Veterinario
import org.springframework.transaction.annotation.Transactional
import org.springframework.dao.DataIntegrityViolationException

@Service
class VeterinarioServiceImpl(
    private val veterinarioRepository: VeterinarioRepository,

    ): VeterinarioServiceInterface{

    override fun criarVeterinario(veterinario: Veterinario): Veterinario {
        val cpfExistente = veterinarioRepository.findByCpf(veterinario.cpf)
        val crmvExistente = veterinarioRepository.findByCrmv(veterinario.crmv)

        if (cpfExistente.isPresent){
            throw Exception("Veterinario ja existente com esse CPF")
        }

        else if (crmvExistente.isPresent){
            throw Exception("Veterinario ja existente com esse CRMV")
        }

        return veterinarioRepository.save(veterinario)
    }

    override fun buscarVeterinarioPorId(id: Long): Veterinario {
        return veterinarioRepository.findById(id).orElse(null)
    }

    override fun buscarAllVeterinario(): List<Veterinario> {
        return veterinarioRepository.findAll()
    }

    override fun atualizar(id: Long, dadosAtualizados: Veterinario): Veterinario {
        val veterinarioExistente = veterinarioRepository.findById(id)
            .orElseThrow { RuntimeException("Veterinário não encontrado") }

        val atualizado = veterinarioExistente.copy(
            nome = if (dadosAtualizados.nome.isNotBlank()) dadosAtualizados.nome else veterinarioExistente.nome,

            cpf = if (dadosAtualizados.cpf.isNotBlank()) dadosAtualizados.cpf else veterinarioExistente.cpf,

            crmv = if (dadosAtualizados.crmv.isNotBlank()) dadosAtualizados.crmv else veterinarioExistente.crmv,

            // Para campos opcionais (nullable), checamos se não é nulo e não é vazio
            endereco = if (!dadosAtualizados.endereco.isNullOrBlank()) dadosAtualizados.endereco else veterinarioExistente.endereco,

            telefone = if (!dadosAtualizados.telefone.isNullOrBlank()) dadosAtualizados.telefone else veterinarioExistente.telefone
        )

        return veterinarioRepository.save(atualizado)
    }

    @Transactional
    override fun deletar(id: Long) {
        if (!veterinarioRepository.existsById(id)) {
            throw RuntimeException("Veterinário não encontrado")
        }

        try {
            veterinarioRepository.deleteById(id)
            // Se tiver consulta vinculada, vai explodir o erro AQUI.
            veterinarioRepository.flush()

        } catch (e: DataIntegrityViolationException) {
            // Captura o erro do banco (chave estrangeira) e lança um aviso claro
            throw RuntimeException("Não é possível deletar: Veterinário possui consultas vinculadas.")
        }
    }


    override fun buscarPorNome(parteDoNome: String): List<Veterinario> {
        return veterinarioRepository.findByNomeContaining(parteDoNome)
    }

    override fun buscarPorCrmv(crmv: String): Veterinario? {
        return veterinarioRepository.findByCrmv(crmv).orElse(null)
    }
}