package com.itau.demo.controller;

import com.itau.demo.model.Conta;
import com.itau.demo.model.Transferencia;
import com.itau.demo.model.TransferenciaResponse;
import com.itau.demo.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping("/health")
    public ResponseEntity<Object> getTest(){
        return ResponseEntity.ok("Ok");
    }

    @GetMapping
    public ResponseEntity<List<Conta>> getAll(){
        List<Conta> response = contaService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{numeroConta}")
    public ResponseEntity<Conta> get(@PathVariable("numeroConta") String numeroConta){
        Conta response = contaService.get(numeroConta);
        if(response == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Conta> create(@RequestBody Conta conta){
        Conta response = contaService.create(conta);
        if(response == null) return new ResponseEntity(HttpStatus.CONFLICT);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/transferencia")
    @Transactional
    public ResponseEntity transferencia(@RequestBody Transferencia transferencia){
        TransferenciaResponse response = contaService.transferir(transferencia);
        return new ResponseEntity(response, response.getStatus());
    }

    @GetMapping("/transferencia/{numeroConta}")
    public ResponseEntity transferencia(@PathVariable("numeroConta") String numeroConta){
        List<Transferencia> response = contaService.getTransferencias(numeroConta);
        if (response == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(response);
    }
}
