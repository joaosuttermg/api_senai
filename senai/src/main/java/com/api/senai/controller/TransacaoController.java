package com.api.senai.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.senai.classes.Transacao;
import com.api.senai.service.ContaBancariaService;
import com.api.senai.service.TransacaoService;




import java.util.List;
public class TransacaoController {
    
    @Autowired
    TransacaoService transacaoService;

    @Autowired
    private ContaBancariaService contaBancariaService;

    @GetMapping
     public ResponseEntity<Transacao> getById(@PathVariable Long id) {
        return ResponseEntity.ok(transacaoService.getById(id));
    }
    @GetMapping("/extrato/{id}")
    public ResponseEntity<List<Transacao>> getExtrato (@PathVariable Long idConta) {
        List<Transacao> extrato = transacaoService.getExtrato(idConta);

        if (extrato.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(extrato);
    }
    @PostMapping
    public ResponseEntity<Transacao> create(@RequestBody Transacao transacao) {
        // Verificar se alguma das contas da transação são nulas
        if(contaBancariaService.temSaldo(transacao)){
            return ResponseEntity.ok(transacaoService.create(transacao));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<Transacao>> getAll(){
        return ResponseEntity.ok(transacaoService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Transacao> getByid(@PathVariable Long id){
        return ResponseEntity.ok(transacaoService.getById(id));
    }
    // @PutMapping("/{id}")
    // public ResponseEntity<Transacao> update(@PathVariable Long id, @RequestBody Transacao transacao){
    //     return ResponseEntity.ok(transacaoService.update(id, transacao));
    // }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        transacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}