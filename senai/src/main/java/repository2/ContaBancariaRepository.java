package repository2;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.senai.classes.ContaBancaria;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {

}
