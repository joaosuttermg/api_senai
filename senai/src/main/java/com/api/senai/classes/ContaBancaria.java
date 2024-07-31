package com.api.senai.classes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

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
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "contasBancarias")
public class ContaBancaria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numeroConta;
    
    @Column(nullable = false)
    private Double saldo;
    
    @OneToOne
    @JoinColumn(name = "nome")
    private String dono;
    
    @Column(nullable = false)
    private LocalDate datacriacao;
    

    

}
