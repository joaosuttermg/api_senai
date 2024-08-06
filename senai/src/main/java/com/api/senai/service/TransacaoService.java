package com.api.senai.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.senai.classes.ContaBancaria;
import com.api.senai.classes.Transacao;
import com.api.senai.repository.ContaBancariaRepository;
import com.api.senai.repository.TransacaoRepository;

@Service
public class TransacaoService {

 @Autowired
 TransacaoRepository transacaoRepository;

 @Autowired
 ContaBancariaService contaBancariaService;

 public List<Transacao> getAll(){
    return transacaoRepository.findAll();
 }
 public Transacao getById(Long id){
    return transacaoRepository.findById(id).orElse(null);
 }
 
  public List<Transacao> getExtrato(Long id) {
        // Tratar possível erro de retorno null
        ContaBancaria conta = contaBancariaService.getById(id);

        return transacaoRepository.findByContaOrigemOrContaDestinoOrderByDataDesc(conta, conta);
    }
    public Transacao create(Transacao novaTransacao) {
      // Tratar a possibilidade de valores nulos nos atributos da transacao
      Transacao transacao = new Transacao();

      ContaBancaria contaOrigem = contaBancariaService.getById(novaTransacao.getContaOrigem().getNumConta());
      ContaBancaria contaDestino = contaBancariaService.getById(novaTransacao.getContaDestino().getNumConta());

      contaOrigem.sacar(novaTransacao.getValor());
      contaDestino.depositar(novaTransacao.getValor());

      contaBancariaService.create(contaDestino);
      contaBancariaService.create(contaOrigem);

      transacao.setValor(novaTransacao.getValor());
      transacao.setContaDestino(contaBancariaService.getById(novaTransacao.getContaDestino().getNumConta()));
      transacao.setContaOrigem(contaBancariaService.getById(novaTransacao.getContaOrigem().getNumConta()));
      transacao.setTipoTransacao(novaTransacao.getTipoTransacao());

      return transacaoRepository.save(transacao);
  }

//  public Transacao update(Long id, Transacao transacao){
//     Transacao transacaoAtualizar = transacaoRepository.findById(id).orElse(null);
//     if (transacaoAtualizar == null) {
//         return null;
//         }
//         return transacaoRepository.save(transacaoAtualizar);
//  }
 public void delete(Long id){
    transacaoRepository.deleteById(id);
 }


}