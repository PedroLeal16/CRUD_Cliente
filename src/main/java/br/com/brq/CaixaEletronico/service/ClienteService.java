package br.com.brq.CaixaEletronico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brq.CaixaEletronico.models.Cliente;
import br.com.brq.CaixaEletronico.repositories.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    ClienteRepository cr;

    public String salvar(Cliente c) {

        List<Cliente> clientes = buscarAll();

        for (Cliente cliente : clientes) {

            if (c.getCpf().equals(cliente.getCpf())) return "Não foi possível cadastrar pois o cpf já existe";
            
        }

        cr.save(c);
        
        return "Cadastro feito com sucesso";
        
    }

    public String atualizar(Cliente c) {

        int s = validaCliente(c);

        switch (s) {
            case 1:
            return "Id de cliente não preenchido, não foi possível atualizar";                

            case 2:
            return "CPF de cliente não preenchido, não foi possível atualizar";

            case 3:
            return "Nome de cliente não preenchido, não foi possível atualizar";

            case 4:
            return "Password não preenchida, não foi possível atualizar";
        
            default:
                break;
        }

        Cliente cliente = buscarCliente(c.getId());

        if (cliente.getId() == null) return "Não foi possível encontrar o cliente";

        cliente.setCpf(c.getCpf());
        cliente.setName(c.getName());
        cliente.setPassword(c.getPassword());

        cr.save(cliente);

        return "Atualização feita com sucesso";

    }
    
    
    public String deletar(Long id) {

        if (id == null) return "Id é um campo obrigatório";
        
        Optional<Cliente> op = cr.findById(id);
        
        Cliente c = op.get();
        
        if (c.getId() == null) {
            
            return "Não foi possível encontrar o cliente";
            
        }
        
        cr.delete(c);
        
        return "Cliente deletado com sucesso";
        
        
    }
    
    public Cliente buscarCliente(Long id) {
        
        Optional<Cliente> op = cr.findById(id);
        Cliente cliente = op.get();
        
        return cliente;
        
    }
    
    public List<Cliente> buscarAll() {
        
        return cr.findAll();
        
    }
    
    private int validaCliente(Cliente c) {
        
        if (c.getId() == null) return 1;
        
        if(c.getCpf().equals("")) return 2;

        if (c.getName().equals("")) return 3;

        if (c.getPassword().equals("")) return 4;
        
        return 0;
       
    }
    
}
