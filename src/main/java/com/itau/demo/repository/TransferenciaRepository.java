package com.itau.demo.repository;
import com.itau.demo.model.Conta;
import com.itau.demo.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {
}
