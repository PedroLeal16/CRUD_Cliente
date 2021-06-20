package br.com.brq.CaixaEletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.brq.CaixaEletronico.models.Cliente;
import br.com.brq.CaixaEletronico.repositories.ClienteRepository;
import br.com.brq.CaixaEletronico.service.ClienteService;

public class ClienteServiceTests {

    @InjectMocks
	private ClienteService cs;

	@Mock
	private ClienteRepository cr;

	@BeforeEach
	public void init() {
		
		MockitoAnnotations.openMocks(this);

	}

	@Test
	public void salvar() {

		when(cr.findAll()).thenReturn(getMockClientesRequest());

		String s = cs.salvar(getMockClienteCertoRequest());

		assertEquals("Cadastro feito com sucesso", s);
		
	}

	@Test
	public void nãoSalvar() {

		when(cr.findAll()).thenReturn(getMockClientesRequest());

		String s = cs.salvar(getMockClienteErradoRequest());

		assertEquals("Não foi possível cadastrar pois o cpf já existe", s);
		
	}

	
	@Test
	public void Atualizar() {
		
		
		when(cr.findById(anyLong())).thenReturn(getMockClienteOptionalRequest());
		
		String s = cs.atualizar(getMockClienteCertoRequest());
		
		assertEquals("Atualização feita com sucesso", s);
		
	}
	
	@Test
	public void nãoAtualizarIdNulo() {
		
		
		when(cr.findById(anyLong())).thenReturn(getMockClienteOptionalRequest());
		
		String s = cs.atualizar(getMockClienteIdErroRequest());
		
		assertEquals("Id de cliente não preenchido, não foi possível atualizar", s);
		
	}
	
	@Test
	public void nãoAtualizarCpfNulo() {
		
		
		when(cr.findById(anyLong())).thenReturn(getMockClienteOptionalRequest());
		
		String s = cs.atualizar(getMockClienteCpfErroRequest());
		
		assertEquals("CPF de cliente não preenchido, não foi possível atualizar", s);
		
	}
	
	@Test
	public void nãoAtualizarNomeNulo() {
		
		
		when(cr.findById(anyLong())).thenReturn(getMockClienteOptionalRequest());
		
		String s = cs.atualizar(getMockClienteNameErroRequest());
		
		assertEquals("Nome de cliente não preenchido, não foi possível atualizar", s);
		
	}
	
	@Test
	public void nãoAtualizarPasswordNulo() {
		
		
		when(cr.findById(anyLong())).thenReturn(getMockClienteOptionalRequest());
		
		String s = cs.atualizar(getMockClientePasswordErroRequest());
		
		assertEquals("Password não preenchida, não foi possível atualizar", s);
		
	}

	@Test
	public void nãoAtualizarIdNãoEncontrado() {

		when(cr.findById(anyLong())).thenReturn(getMockClienteOptionalVazioRequest());
		
		String s = cs.atualizar(getMockClienteCertoRequest());
		
		assertEquals("Não foi possível encontrar o cliente", s);
		
	}

	@Test
	public void deletar() {

		when(cr.findById(anyLong())).thenReturn(getMockClienteOptionalRequest());

		String s = cs.deletar(getMockClienteCertoRequest().getId());
		
		assertEquals("Cliente deletado com sucesso", s);
	}

	@Test
	public void nãoDeletar() {

		when(cr.findById(anyLong())).thenReturn(getMockClienteOptionalVazioRequest());

		String s = cs.deletar(getMockClienteCertoRequest().getId());
		
		assertEquals("Não foi possível encontrar o cliente", s);
	}

	@Test
	public void nãoDeletarIdNulo() {

		when(cr.findById(anyLong())).thenReturn(getMockClienteOptionalVazioRequest());

		String s = cs.deletar(getMockClienteIdErroRequest().getId());
		
		assertEquals("Id é um campo obrigatório", s);
	}
	
	private Cliente getMockClienteCertoRequest() {
		
		Cliente request = new Cliente();
		request.setCpf("46864816869");
		request.setName("Pedro Leal");
		request.setPassword("1606");
		request.setId(3L);
		
		return request;
	}
	
	private Cliente getMockClienteIdErroRequest() {
		
		Cliente request = new Cliente();
		request.setCpf("46864816870");
		request.setName("Pedro Leal");
		request.setPassword("1606");
		request.setId(null);
		
		return request;
	}
	
	private Cliente getMockClienteCpfErroRequest() {
		
		Cliente request = new Cliente();
		request.setCpf("");
		request.setName("Pedro Leal");
		request.setPassword("1606");
		request.setId(1L);
		
		return request;
	}
	
	private Cliente getMockClienteNameErroRequest() {
		
		Cliente request = new Cliente();
		request.setCpf("46864816870");
		request.setName("");
		request.setPassword("1606");
		request.setId(1L);
		
		return request;
	}
	
	private Cliente getMockClientePasswordErroRequest() {
		
		Cliente request = new Cliente();
		request.setCpf("46864816870");
		request.setName("Pedro Leal");
		request.setPassword("");
		request.setId(1L);
		
		return request;
	}
	
	private Optional<Cliente> getMockClienteOptionalRequest() {
		
		
		Cliente request = new Cliente();
		request.setCpf("46864816875");
		request.setName("Pedro");
		request.setPassword("162320");
		request.setId(3L);
		
		Optional<Cliente> op = Optional.of(request);
		
		return op;
	}

	private Optional<Cliente> getMockClienteOptionalVazioRequest() {
		
		
		Cliente request = new Cliente();
		
		Optional<Cliente> op = Optional.of(request);
		
		return op;
	}
	
	private List<Cliente> getMockClientesRequest() {
		
		List<Cliente> clientes = new ArrayList<>();
		
		Cliente request = new Cliente();
		request.setCpf("46864816871");
		request.setName("Pedro Henrique");
		request.setPassword("1625");
		request.setId(1L);
		clientes.add(request);
		
		Cliente request2 = new Cliente();
		request2.setCpf("46864816870");
		request2.setName("Pedro Santiago");
		request2.setPassword("2507");
		request2.setId(2L);
		clientes.add(request2);
		
		return clientes;
	}
	
	private Cliente getMockClienteErradoRequest() {
		
		Cliente request = new Cliente();
		request.setCpf("46864816871");
		request.setName("Pedro Leal");
		request.setPassword("162320");
		request.setId(3L);

		return request;

	}
    
}
