package com.api.senai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.senai.service.EnderecoService;
import com.api.senai.classes.Endereco;


public class EnderecoController {
    @Autowired
    EnderecoService enderecoService;

    public ResponseEntity<Endereco> create(@RequestBody Endereco endereco) {
        return ResponseEntity.ok(enderecoService.create(endereco));
    }

    @PostMapping("/cep/{cep}")
    public ResponseEntity<Endereco> createEnderecoByCep(@PathVariable String cep) {
        // Tipo de resposta quando der erro
        return ResponseEntity.ok(enderecoService.getEnderecoByCep(cep));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> update(@PathVariable Long id, @RequestBody Endereco endereco) {
        return ResponseEntity.ok(enderecoService.update(id, endereco));

    }
}
