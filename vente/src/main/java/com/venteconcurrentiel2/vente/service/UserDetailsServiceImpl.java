package com.venteconcurrentiel2.vente.service;

import com.venteconcurrentiel2.vente.model.Client;
import com.venteconcurrentiel2.vente.model.Fournisseur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private FournisseurService fournisseurService;

    public UserDetailsServiceImpl(ClientService clientService, FournisseurService fournisseurService) {
        this.clientService = clientService;
        this.fournisseurService = fournisseurService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Check if username exists as client or fournisseur
        Client client = clientService.findByMail(username);
        if (client != null) {
            return new User(client.getMailCli(), client.getMdpCli(), AuthorityUtils.createAuthorityList(client.getRole()));
        }

        Fournisseur fournisseur = fournisseurService.findByMail(username);
        if (fournisseur != null) {
            return new User(fournisseur.getMailFr(), fournisseur.getMdpFr(), AuthorityUtils.createAuthorityList(fournisseur.getRole()));
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}

