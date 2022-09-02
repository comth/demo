package com.itau.demo.service;

import com.itau.demo.model.Conta;
import com.itau.demo.model.Transferencia;
import com.itau.demo.repository.ContaRepository;
import com.itau.demo.repository.TransferenciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContaService {

    private final TransferenciaRepository transferenciaRepository;
    private final ContaRepository contaRepository;

    public ContaService(TransferenciaRepository transferenciaRepository, ContaRepository contaRepository) {
        this.transferenciaRepository = transferenciaRepository;
        this.contaRepository = contaRepository;
    }

    public List<Conta> getAll(){
        return this.contaRepository.findAll();
    }

    public Conta get(String numeroConta) {
        return this.contaRepository.getByNumero(numeroConta);
    }

    public Conta create(Conta conta) {
        return this.contaRepository.save(conta);
    }

    @Transactional
    public Transferencia transferir(Transferencia transferencia) {
        if(transferencia.getValor() > 1000) throw new Error("Valor acima do permitido");

        Conta remetente = get(transferencia.getRemetente().getNumero());
        Conta destinatario = get(transferencia.getDestinatario().getNumero());

        if(remetente.getSaldo() < transferencia.getValor()) throw new Error("Sem saldo suficiente na conta " + remetente.getNumero());

        remetente.setSaldo(remetente.getSaldo() - transferencia.getValor());
        destinatario.setSaldo(remetente.getSaldo() + transferencia.getValor());
        transferencia.setEfetivada(true);

        this.contaRepository.save(remetente);
        this.contaRepository.save(destinatario);
        this.transferenciaRepository.save(transferencia);

        return transferencia;
    }
}
