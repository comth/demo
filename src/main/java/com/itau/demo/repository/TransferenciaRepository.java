package com.itau.demo.repository;
import com.itau.demo.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {
    public ArrayList<Transferencia> findByDestinatarioNumero(String numero);
    public ArrayList<Transferencia> findByRemetenteNumero(String numero);
}
