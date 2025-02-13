package com.example.clienteservice;

import com.example.clienteservice.exception.ClienteNoEncontradoException;
import com.example.clienteservice.model.Cliente;
import com.example.clienteservice.repository.ClienteRepository;
import com.example.clienteservice.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceApplicationTests {

	@Mock
	private ClienteRepository clienteRepository;

	@InjectMocks
	private ClienteService clienteService;

	@Test
	void buscarCliente_ExisteCliente_DeberiaRetornarCliente() {
		Cliente cliente = new Cliente(null, "C", "123456789", "Juan", null, "Pérez", null, "3112345678", "Calle 123", "Bogotá", "juan@email.com");
		when(clienteRepository.findByTipoDocumentoAndNumeroDocumento("CC", "123456789"))
				.thenReturn(Optional.of(cliente));

		Optional<Cliente> resultado = clienteService.buscarCliente("CC", "123456789");

		assertTrue(resultado.isPresent());
		assertEquals("Juan", resultado.get().getPrimerNombre());
	}

	@Test
	void buscarCliente_NoExisteCliente_DeberiaRetornarEmpty() {
		when(clienteRepository.findByTipoDocumentoAndNumeroDocumento("CC", "000000000"))
				.thenReturn(Optional.empty());

		Optional<Cliente> resultado = clienteService.buscarCliente("CC", "000000000");

		assertFalse(resultado.isPresent());
	}

	@Test
	void obtenerCliente_ExisteCliente_DeberiaRetornarCliente() {
		Cliente cliente = new Cliente(null, "C", "123456789", "Juan", null, "Pérez", null, "3112345678", "Calle 123", "Bogotá", "juan@email.com");
		when(clienteRepository.findByTipoDocumentoAndNumeroDocumento("CC", "123456789"))
				.thenReturn(Optional.of(cliente));

		Cliente resultado = clienteService.obtenerCliente("CC", "123456789");

		assertNotNull(resultado);
		assertEquals("Juan", resultado.getPrimerNombre());
	}

	@Test
	void obtenerCliente_NoExisteCliente_DeberiaLanzarExcepcion() {
		when(clienteRepository.findByTipoDocumentoAndNumeroDocumento("CC", "000000000"))
				.thenReturn(Optional.empty());

		ClienteNoEncontradoException exception = assertThrows(
				ClienteNoEncontradoException.class,
				() -> clienteService.obtenerCliente("CC", "000000000")
		);

		assertEquals("Cliente no encontrado con documento: CC-000000000", exception.getMessage());
	}

	@Test
	void actualizarCliente_ExisteCliente_DeberiaActualizarSoloCamposNoNulos() {
		Cliente clienteExistente = new Cliente(null, "C", "123456789", "Miguel", null, "Pérez", null, "3112345678", "Calle 123", "Bogotá", "juan@email.com");
		Cliente clienteActualizado = new Cliente(null, "C", "123456789", "Miguel", null, "Pérez", null, "3112345678", "Calle 123", "Bogotá", "juan@email.com");
		clienteActualizado.setDireccion("Calle 123");
		clienteActualizado.setTelefono("3112345678");

		when(clienteRepository.findByTipoDocumentoAndNumeroDocumento("CC", "123456789"))
				.thenReturn(Optional.of(clienteExistente));
		when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteExistente);

		Cliente resultado = clienteService.actualizarCliente("CC", "123456789", clienteActualizado);

		assertEquals("Miguel", resultado.getPrimerNombre());
		assertEquals("Pérez", resultado.getPrimerApellido());
		assertEquals("Calle 123", resultado.getDireccion());
		assertEquals("3112345678", resultado.getTelefono());
	}

	@Test
	void actualizarCliente_NoExisteCliente_DeberiaLanzarExcepcion() {
		Cliente clienteActualizado = new Cliente(null, "C", "123456789", "Juan", null, "Pérez", null, "3112345678", "Calle 123", "Bogotá", "juan@email.com");

		when(clienteRepository.findByTipoDocumentoAndNumeroDocumento("CC", "123456789"))
				.thenReturn(Optional.empty());

		ClienteNoEncontradoException exception = assertThrows(
				ClienteNoEncontradoException.class,
				() -> clienteService.actualizarCliente("CC", "123456789", clienteActualizado)
		);

		assertEquals("Cliente no encontrado con documento: CC-123456789", exception.getMessage());
	}

}
