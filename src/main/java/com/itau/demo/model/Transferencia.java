package com.itau.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Transferencia implements Comparable<Transferencia>{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Schema(accessMode = AccessMode.READ_ONLY)
    private Integer id;
    @ManyToOne
    private Conta remetente;
    @ManyToOne
    private Conta destinatario;
    private Float valor;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private Boolean efetivada = false;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private Date data = new Date();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Conta getRemetente() {
        return remetente;
    }

    public void setRemetente(Conta remetente) {
        this.remetente = remetente;
    }

    public Conta getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Conta destinatario) {
        this.destinatario = destinatario;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Boolean getEfetivada() {
        return efetivada;
    }

    public void setEfetivada(Boolean efetivada) {
        this.efetivada = efetivada;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public int compareTo(Transferencia t) {
        return t.getData().compareTo(getData());
    }
}
