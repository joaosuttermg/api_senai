package com.api.senai.classes;
import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(of = "numConta")
@Entity
@Table(name = "contas_bancarias")
public class ContaBancaria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numConta;
    
    @Column(nullable = false)
    private Double saldo = 0.0;
    
    @OneToOne
    @JoinColumn(name = "dono_da_conta", referencedColumnName = "id")
    private Cliente dono;
    
    @Column(nullable = false)
    private LocalDate datacriacao;

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    private Cliente titular;

    public void depositar(double valor) {
        saldo += valor;
    }
    public void sacar(double valor) {
        saldo -= valor;
    }

}

    

    
    

    


