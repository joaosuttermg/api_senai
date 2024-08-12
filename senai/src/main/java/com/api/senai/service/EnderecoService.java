package com.api.senai.service;

import java.util.List;
import com.google.gson.Gson;

import com.api.senai.classes.Endereco;
import com.api.senai.repository.EnderecoRepository;

import org.springframework.beans.factory.annotation.Autowired;


public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    ViaCepService viaCepService;

    public List<Endereco> getAll() {
        return enderecoRepository.findAll();
    }

    public Endereco getById(Long id) {
        return enderecoRepository.findById(id).orElse(null);
    }

    public Endereco getEnderecoByCep(String cep) {
        // Adicionar os tratamentos de erro
        System.out.println("Buscando CEP: " + cep);
        Endereco endereco = viaCepService.getEnderecoByCep(cep);

        // Remove o hifen do String cep
        endereco.setCep(endereco.getCep()
                .replace("-", ""));

        Gson gson = new Gson();
        System.out.println("Endereco: " + endereco.toString());
        return enderecoRepository.save(endereco);
    }

    public Endereco create(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }
     public Endereco update(Long id, Endereco endereco) {
        Endereco enderecoExistente = getById(id);

        if (enderecoExistente == null) {
            return null;
        }

        enderecoExistente.setCep(endereco.getCep());
        enderecoExistente.setLogradouro(endereco.getLogradouro());
        enderecoExistente.setBairro(endereco.getBairro());
        enderecoExistente.setLocalidade(endereco.getLocalidade());
        enderecoExistente.setComplemento(endereco.getComplemento());
        enderecoExistente.setUf(endereco.getUf());

        return enderecoRepository.save(enderecoExistente);
    }


}
