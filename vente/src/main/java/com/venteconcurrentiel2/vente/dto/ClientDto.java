package com.venteconcurrentiel2.vente.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {
    private String pseudo;
    private String mdpCli;
    private String mailCli;
    private String contact;
    private String adresse;
}
