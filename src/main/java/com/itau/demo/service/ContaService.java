package com.itau.demo.service;

import com.itau.demo.model.Conta;
import com.itau.demo.model.Transferencia;
import com.itau.demo.repository.ContaRepository;
import com.itau.demo.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;
    @Autowired
    private ContaRepository contaRepository;


    public List<Conta> getAll(){
        return this.contaRepository.findAll();
    }

    public Conta get(String numeroConta) {
        return this.contaRepository.getByNumero(numeroConta);
    }

    public Conta create(Conta conta) {
        return this.contaRepository.save(conta);
    }

    public Transferencia transferir(Transferencia transferencia) {
        Conta remetente = get(transferencia.getRemetente().getNumero());
        Conta destinatario = get(transferencia.getDestinatario().getNumero());
        transferencia.setRemetente(remetente);
        transferencia.setDestinatario(destinatario);
        if(transferencia.getValor() > 1000){
            saveTransferencia(transferencia, false);
            throw new Error("Valor acima do permitido");
        }

        if(remetente.getSaldo() < transferencia.getValor()){
            saveTransferencia(transferencia, false);
            throw new Error("Sem saldo suficiente na conta " + remetente.getNumero());
        }

        remetente.setSaldo(remetente.getSaldo() - transferencia.getValor());
        destinatario.setSaldo(destinatario.getSaldo() + transferencia.getValor());

        transferencia = saveTransferencia(transferencia, true);
        remetente.getTransferencias().add(transferencia);
        destinatario.getTransferencias().add(transferencia);
        return transferencia;
    }

    private Transferencia saveTransferencia(Transferencia transferencia, Boolean efetivada){
        transferencia.setEfetivada(efetivada);
        return this.transferenciaRepository.save(transferencia);
    }
}
