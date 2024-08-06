package com.api.senai.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.senai.classes.ContaBancaria;
import com.api.senai.classes.Transacao;
import com.api.senai.repository.*;



@Service
public class ContaBancariaService {

    @Autowired
    private  com.api.senai.repository.ContaBancariaRepository ContaBancariaRepository;

    public List<ContaBancaria> getAll() {
        return ContaBancariaRepository.findAll();
    }
    public  ContaBancaria getById(Long numConta) {
        return ContaBancariaRepository.findById(numConta)
                                .orElse(null);
    }
     public boolean temSaldo(Transacao transacao) {
        // Comprarar o saldo da conta origem com o valor da transacao
        ContaBancaria conta = getById(transacao.getContaOrigem().getNumConta());

        boolean temSaldo = (
            conta.getSaldo() 
            >= 
            transacao.getValor()
        );
        return temSaldo;
    }



    public ContaBancaria create(ContaBancaria contaBancaria) {
        return ContaBancariaRepository.save(contaBancaria);
    }
    public void delete(Long numConta) {
        ContaBancariaRepository.deleteById(numConta);
    }
    public ContaBancaria update(ContaBancaria contaExistente, ContaBancaria contaNova) {

        contaExistente.setSaldo(contaNova.getSaldo());

        contaExistente.setNumConta(contaNova.getNumConta());
        contaExistente.setSaldo(contaNova.getSaldo());
        contaExistente.setDono(contaNova.getDono());
        contaExistente.setDatacriacao(contaNova.getDatacriacao());
        
    
            return ContaBancariaRepository.save(contaExistente);
        
    }
        
    }

