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
@Table(name = "Transacoes")

public class Transacao {

}
