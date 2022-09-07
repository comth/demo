package com.itau.demo.model;

import org.springframework.http.HttpStatus;

public class TransferenciaResponse {
    private Transferencia transferencia;
    private String message;
    private HttpStatus status;

    public TransferenciaResponse(Transferencia transferencia, String message, HttpStatus status) {
        this.transferencia = transferencia;
        this.message = message;
        this.status = status;
    }

    public Transferencia getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(Transferencia transferencia) {
        this.transferencia = transferencia;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
