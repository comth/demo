package com.itau.demo.controller;

import com.itau.demo.model.Conta;
import com.itau.demo.model.Transferencia;
import com.itau.demo.model.TransferenciaResponse;
import com.itau.demo.service.ContaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Operation(summary = "Verifica se a aplicação está rodando")
    @GetMapping("/health")
    public ResponseEntity<Object> getTest(){
        return ResponseEntity.ok("Ok");
    }

    @Operation(summary = "Lista as Contas")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ok", 
          content = { @Content(mediaType = "application/json", 
            array = @ArraySchema( schema = @Schema(implementation = Conta.class))) }),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @GetMapping
    public ResponseEntity<List<Conta>> getAll(){
        List<Conta> response = contaService.getAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Retorna os dados de uma Conta específica")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ok", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Conta.class)) }),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @GetMapping("/{numeroConta}")
    public ResponseEntity<Conta> get(@PathVariable("numeroConta") String numeroConta){
        Conta response = contaService.get(numeroConta);
        if(response == null) return new ResponseEntity<Conta>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cadastra uma nova Conta")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ok", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Conta.class)) }),
        @ApiResponse(responseCode = "409", description = "Conflict", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @PostMapping
    public ResponseEntity<Conta> create(@RequestBody Conta conta){
        Conta response = contaService.create(conta);
        if(response == null) return new ResponseEntity<Conta>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cadastra uma nova Tranferência", 
        description = "Somente o número das contas é requerido para essa operação")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ok", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = TransferenciaResponse.class)) }),
        @ApiResponse(responseCode = "404", description = "Not Found",
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = TransferenciaResponse.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request",
        content = { @Content(mediaType = "application/json", 
          schema = @Schema(implementation = TransferenciaResponse.class)) }),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @PostMapping("/transferencia")
    @Transactional
    public ResponseEntity<TransferenciaResponse> transferencia(@RequestBody Transferencia transferencia){
        TransferenciaResponse response = contaService.transferir(transferencia);
        return new ResponseEntity<TransferenciaResponse>(response, response.getStatus());
    }

    @Operation(summary = "Listar Transferências de uma Conta")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ok", 
          content = { @Content(mediaType = "application/json", 
            array = @ArraySchema( schema = @Schema(implementation = Transferencia.class))) }),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @GetMapping("/transferencia/{numeroConta}")
    public ResponseEntity<List<Transferencia>> transferencia(@PathVariable("numeroConta") String numeroConta){
        List<Transferencia> response = contaService.getTransferencias(numeroConta);
        if (response == null) return new ResponseEntity<List<Transferencia>>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(response);
    }
}
