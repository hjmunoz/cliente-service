package com.example.clienteservice.controller;

import com.example.clienteservice.model.Cliente;
import com.example.clienteservice.service.ClienteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{tipoDocumento}/{numeroDocumento}")
    public Cliente obtenerCliente(
            @PathVariable @Pattern(regexp = "C|P", message = "El tipo de documento debe ser 'C' o 'P'") String tipoDocumento,
            @PathVariable @Size(min = 5, max = 15, message = "El número de documento debe tener entre 5 y 15 caracteres") String numeroDocumento) {
        return clienteService.obtenerCliente(tipoDocumento, numeroDocumento);
    }

    @PutMapping("/{tipoDocumento}/{numeroDocumento}")
    public ResponseEntity<Cliente> actualizarCliente(
            @PathVariable String tipoDocumento,
            @PathVariable String numeroDocumento,
            @Valid @RequestBody Cliente clienteActualizado) {

        Cliente cliente = clienteService.actualizarCliente(tipoDocumento, numeroDocumento, clienteActualizado);
        return ResponseEntity.ok(cliente);
    }
}
