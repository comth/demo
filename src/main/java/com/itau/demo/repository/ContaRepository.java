package com.itau.demo.repository;
import com.itau.demo.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
    @Query("SELECT t FROM Conta t WHERE t.numero = ?1")
    Conta getByNumero(String numero);
}
