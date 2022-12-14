package com.moneyawapi.service;

import com.moneyawapi.model.Lancamento;
import com.moneyawapi.model.Pessoa;
import com.moneyawapi.repository.LancamentoRepository;
import com.moneyawapi.repository.PessoaRepository;
import com.moneyawapi.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRespository;

    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaRespository.findById(lancamento.getPessoa().getCodigo()).orElseThrow(null);

        if (pessoa == null || !pessoa.getAtivo()) {
            throw new PessoaInexistenteOuInativaException();
        }

        return lancamentoRepository.save(lancamento);

    }
}
