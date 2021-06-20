package br.com.brq.CaixaEletronico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brq.CaixaEletronico.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
