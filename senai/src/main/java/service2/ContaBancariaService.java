package service2;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.senai.classes.ContaBancaria;



@Service
public class ContaBancariaService {

    @Autowired
    private repository2.ContaBancariaRepository ContaBancariaRepository;

    public List<ContaBancaria> getAll() {
        return ContaBancariaRepository.findAll();
    }
    public ContaBancaria getById(Long id) {
        return ContaBancariaRepository.findById(id)
                                .orElse(null);
    }

    public ContaBancaria create(ContaBancaria contaBancaria) {
        return ContaBancariaRepository.save(contaBancaria);
    }

}
