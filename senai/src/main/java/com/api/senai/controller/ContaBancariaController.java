package com.api.senai.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;



import org.springframework.web.bind.annotation.*;


import com.api.senai.classes.ContaBancaria;
import com.api.senai.service.ContaBancariaService;

@RestController
@RequestMapping("ContasBancarias")
public class ContaBancariaController {
    @Autowired
    private ContaBancariaService contaBancariaService;

    @GetMapping
    public ResponseEntity<List<ContaBancaria>> getAll() {
        List<ContaBancaria> contaBancarias = contaBancariaService.getAll();
        return ResponseEntity.ok(contaBancarias);
    }

    // Buscar uma conta por id - getById
    @GetMapping("numConta")
    public ResponseEntity<ContaBancaria> getById(@PathVariable Long numConta) {
        ContaBancaria contaBancaria = contaBancariaService.getById(numConta);
        if (contaBancaria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contaBancaria);
    }

    // Criar uma conta - create
    @PostMapping
    public ResponseEntity<ContaBancaria> create(@RequestBody ContaBancaria contaBancaria) {
        ContaBancaria contaSalva = contaBancariaService.create(contaBancaria);
        return ResponseEntity.ok(contaSalva);
    }

    // Atualizar um cliente - update
    // Combinação do getById e create
    @PutMapping("numConta")
    public ResponseEntity<ContaBancaria> update(@PathVariable Long numConta, @RequestBody ContaBancaria contaNova) {
        ContaBancaria contaExistente = contaBancariaService.getById(numConta);

        if (contaExistente == null) {
            return ResponseEntity.notFound().build();
        }

    contaExistente.setNumConta(contaNova.getNumConta());
    contaExistente.setSaldo(contaNova.getSaldo());
    contaExistente.setDono(contaNova.getDono());
    contaExistente.setDatacriacao(contaNova.getDatacriacao());
    

        ContaBancaria contaSalva = contaBancariaService.create(contaExistente);

        return ResponseEntity.ok(contaSalva);
    }

    // Deletar uma conta - delete
    @DeleteMapping("numConta")
    public ResponseEntity<Void> delete(@PathVariable Long numConta) {
        ContaBancaria contaBancaria = contaBancariaService.getById(numConta);

        if (contaBancaria == null) {
            return ResponseEntity.notFound().build();
        }

        contaBancariaService.delete(numConta);

        return ResponseEntity.noContent().build();
    }

}



