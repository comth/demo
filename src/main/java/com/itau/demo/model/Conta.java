package com.itau.demo.model;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

import javax.persistence.*;

@Entity
public class Conta {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Schema(accessMode = AccessMode.READ_ONLY)
    private Integer id;
    @Column(unique = true)
    private String numero;
    private String nomeCliente;
    private Float saldo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }
}
