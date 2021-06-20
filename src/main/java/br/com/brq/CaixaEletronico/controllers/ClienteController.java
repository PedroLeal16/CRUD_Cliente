package br.com.brq.CaixaEletronico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.brq.CaixaEletronico.models.Cliente;
import br.com.brq.CaixaEletronico.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService cs;

    @PostMapping("/cadastro")
    public ResponseEntity<Cliente> criar(@RequestBody Cliente c ) {

        Cliente cliente = new Cliente(c.getName(), c.getCpf(), c.getPassword());

        cs.salvar(cliente);

        return ResponseEntity.ok().body(c);

    }

    @PutMapping()
    public ResponseEntity atualizar(@RequestBody Cliente c) {

        cs.atualizar(c);

        return ResponseEntity.ok().build();
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {

        cs.deletar(id);

        return ResponseEntity.ok().build();
        
    }
    
}
