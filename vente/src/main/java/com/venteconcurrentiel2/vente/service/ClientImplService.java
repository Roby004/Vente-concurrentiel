package com.venteconcurrentiel2.vente.service;

import com.venteconcurrentiel2.vente.dto.ClientDto;
import com.venteconcurrentiel2.vente.model.Client;
import com.venteconcurrentiel2.vente.repository.ClientRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientImplService implements ClientService{
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public Client enregistrer(ClientDto clientDto) {
        Client client = new Client();
        client.setPseudo(clientDto.getPseudo());
        client.setMdpCli(clientDto.getMdpCli());
        client.setMailCli(clientDto.getMailCli());
        client.setContact(clientDto.getContact());
        client.setAdresse(clientDto.getAdresse());
        client.setRole("ROLE_CLIENT"); // Assuming default role
        client.setAcces(false); // Assuming default access

        return clientRepository.save(client);
    }

    @Override
    public Client findByMail(String mail) {
        return clientRepository.findByMailCli(mail);
    }

    @Override
    public Client validateClient(String mail, String password) {
        Client client = clientRepository.findByMailCli(mail);
        if (client != null && client.getMdpCli().equals(password)) {
            return client;
        }
        return null;
    }

    @Override
    public Client getClientById(Long idCli) {
        Optional<Client> clientOptional = clientRepository.findById(idCli);
        return clientOptional.orElse(null);
    }
}
