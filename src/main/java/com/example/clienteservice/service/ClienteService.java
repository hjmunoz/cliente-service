package com.example.clienteservice.service;

import com.example.clienteservice.exception.ClienteNoEncontradoException;
import com.example.clienteservice.model.Cliente;
import com.example.clienteservice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Optional<Cliente> buscarCliente(String tipoDocumento, String numeroDocumento) {
        return clienteRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento);
    }

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente obtenerCliente(String tipoDocumento, String numeroDocumento) {
        return clienteRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado con documento: " + tipoDocumento + "-" + numeroDocumento));
    }

    public Cliente actualizarCliente(String tipoDocumento, String numeroDocumento, Cliente clienteActualizado) {
        Cliente cliente = clienteRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado con documento: " + tipoDocumento + "-" + numeroDocumento));

        // Actualizar los datos del cliente
        cliente.setPrimerNombre(clienteActualizado.getPrimerNombre());
        cliente.setSegundoNombre(clienteActualizado.getSegundoNombre());
        cliente.setPrimerApellido(clienteActualizado.getPrimerApellido());
        cliente.setSegundoApellido(clienteActualizado.getSegundoApellido());
        cliente.setTelefono(clienteActualizado.getTelefono());
        cliente.setDireccion(clienteActualizado.getDireccion());
        cliente.setCiudadResidencia(clienteActualizado.getDireccion());

        if (clienteActualizado.getPrimerNombre() != null) {
            cliente.setPrimerNombre(clienteActualizado.getPrimerNombre());
        }
        if (clienteActualizado.getDireccion() != null) {
            cliente.setDireccion(clienteActualizado.getDireccion());
        }
        if (clienteActualizado.getTelefono() != null) {
            cliente.setTelefono(clienteActualizado.getTelefono());
        }

        return clienteRepository.save(cliente);
    }


}

