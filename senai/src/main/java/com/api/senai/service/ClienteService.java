package com.api.senai.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;


import com.api.senai.classes.Cliente;
import com.api.senai.dto.ClienteDTO;
import com.api.senai.dto.ClienteUpdateDTO;
import com.api.senai.repository.ClienteRepository;
import com.api.senai.service.ClienteService;

@Service

public class ClienteService {
    
    public ClienteUpdateDTO updateDTO(Cliente clienteExistente, ClienteUpdateDTO clienteNovo) {
        
        // Converter o que é DTO pra Cliente
        
        if (clienteNovo.getNome() != null) {
            clienteExistente.setNome(clienteNovo.getNome());
        }
        if (clienteNovo.getTelefone() != null) {
            clienteExistente.setTelefone(clienteNovo.getTelefone());
        }
        if (clienteNovo.getEmail() != null) {
            clienteExistente.setEmail(clienteNovo.getEmail());
        }

        // Atualizar o clienteExistente com os dados do clienteNovo
        Cliente clienteSalvo = clienteRepository.save(clienteExistente);

        // Converter o Cliente pra DTO 
        ClienteUpdateDTO clienteDTO = new ClienteUpdateDTO();
        clienteDTO.setId(clienteSalvo.getId());
        clienteDTO.setNome(clienteSalvo.getNome());
        clienteDTO.setTelefone(clienteSalvo.getTelefone());
        clienteDTO.setEmail(clienteSalvo.getEmail());

        // Retornar
        return clienteDTO;
    }
    
    
    public List<Cliente> getAllAtivos() {
       
        return clienteRepository.findByClienteAtivoTrue();
    }
    
    
    public List<ClienteDTO> getClientesDTO() {

        List<Cliente> clientes = clienteRepository.findAll();

        List<ClienteDTO> clientesDTO = new ArrayList<>();

        for (Cliente cliente : clientes) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setId(cliente.getId());
            clienteDTO.setNome(cliente.getNome());

            clientesDTO.add(clienteDTO);
        }
        return clientesDTO;
    }

    
    private ClienteRepository clienteRepository;

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }
    public Cliente getById(Long id) {
        return clienteRepository.findById(id)
                                .orElse(null);
    }

    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Alternativa de escrever o update na camada de service
    public Cliente update(Long id, Cliente cliente) {
        Cliente clienteExistente = getById(id);

        if (clienteExistente == null) {
            return null;
        }

        clienteExistente.setNome(cliente.getNome());
        clienteExistente.setCpf(cliente.getCpf());
        clienteExistente.setEndereco(cliente.getEndereco());
        clienteExistente.setTelefone(cliente.getTelefone());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setDataNascimento(cliente.getDataNascimento());

        return clienteRepository.save(clienteExistente);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
    
}



