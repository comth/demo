package com.itau.demo.service;

import com.itau.demo.model.Conta;
import com.itau.demo.model.Transferencia;
import com.itau.demo.model.TransferenciaResponse;
import com.itau.demo.repository.ContaRepository;
import com.itau.demo.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        if(contaRepository.existsByNumero(conta.getNumero())){
            return null;
        }
        return this.contaRepository.save(conta);
    }

    public TransferenciaResponse transferir(Transferencia transferencia) {
        Conta remetente = get(transferencia.getRemetente().getNumero());
        Conta destinatario = get(transferencia.getDestinatario().getNumero());

        if (remetente == null || destinatario == null){
            String message = "A Transferência não pôde ser realizada. ";
            if(remetente == null){
                message = message + "Conta " + transferencia.getRemetente().getNumero() + " não encontrada. ";
            }
            if(destinatario == null) {
                message = message + "Conta " + transferencia.getDestinatario().getNumero() + " não encontrada.";
            }
            return new TransferenciaResponse( null, message.trim() , HttpStatus.NOT_FOUND);
        }

        transferencia.setRemetente(remetente);
        transferencia.setDestinatario(destinatario);

        if(transferencia.getValor() > 1000){
            transferencia = saveTransferencia(transferencia, false);
            return new TransferenciaResponse(transferencia, "Valor acima do permitido", HttpStatus.BAD_REQUEST);
        }

        if(remetente.getSaldo() < transferencia.getValor()){
            transferencia = saveTransferencia(transferencia, false);
            String message = "Sem saldo suficiente na conta " + remetente.getNumero();
            return new TransferenciaResponse(transferencia, message, HttpStatus.BAD_REQUEST);
        }

        remetente.setSaldo(remetente.getSaldo() - transferencia.getValor());
        destinatario.setSaldo(destinatario.getSaldo() + transferencia.getValor());

        transferencia = saveTransferencia(transferencia, true);
        return new TransferenciaResponse(transferencia, "Transferencia efetivada", HttpStatus.OK);
    }

    private Transferencia saveTransferencia(Transferencia transferencia, Boolean efetivada){
        transferencia.setEfetivada(efetivada);
        return this.transferenciaRepository.save(transferencia);
    }

    public ArrayList<Transferencia> getTransferencias(String numeroConta) {
        ArrayList<Transferencia> transferenciasRemetente = transferenciaRepository.findByRemetenteNumero(numeroConta);
        ArrayList<Transferencia> transferenciasDestinatario = transferenciaRepository.findByDestinatarioNumero(numeroConta);
        Set<Transferencia> set = new LinkedHashSet<>();
        set.addAll(transferenciasDestinatario);
        set.addAll(transferenciasRemetente);
        ArrayList<Transferencia> transferencias = new ArrayList<Transferencia>(set);
        Collections.sort(transferencias);
        return transferencias;
    }
}
