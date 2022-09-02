package com.itau.demo.controller;

import com.itau.demo.model.Conta;
import com.itau.demo.model.Transferencia;
import com.itau.demo.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping
    public ResponseEntity<List<Conta>> getAll(){
        List<Conta> response = contaService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{numeroConta}")
    public ResponseEntity<Conta> get(@PathVariable("numeroConta") String numeroConta){
        Conta response = contaService.get(numeroConta);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Conta> create(@RequestBody Conta conta){
        Conta response = contaService.create(conta);
        return ResponseEntity.ok(response);
    }

    @PostMapping("transferencia")
    @Transactional
    public ResponseEntity<Transferencia> transferencia(@RequestBody Transferencia transferencia){
        Transferencia response = contaService.transferir(transferencia);
        return ResponseEntity.ok(response);
    }
}
