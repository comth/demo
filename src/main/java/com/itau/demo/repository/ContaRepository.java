package com.itau.demo.repository;
import com.itau.demo.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
    Conta getByNumero(String numero);
    boolean existsByNumero(String numero);
}
