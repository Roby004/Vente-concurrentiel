package com.venteconcurrentiel2.vente.repository;

import com.venteconcurrentiel2.vente.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByMailCli(String mail);
}