package com.venteconcurrentiel2.vente.service;

import com.venteconcurrentiel2.vente.dto.ClientDto;
import com.venteconcurrentiel2.vente.model.Client;

public interface ClientService {
    Client enregistrer(ClientDto clientDto);
    Client findByMail(String mail);
    Client validateClient(String mail, String password);

    Client getClientById(Long idCli);
}
