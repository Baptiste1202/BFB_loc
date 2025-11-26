package com.example.demo.interfaces.clients.model.input;

import com.example.demo.business.clients.model.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Date;

@Data
@NoArgsConstructor
public class ClientUpdateInput {

    @Serial
    private static final long serialVersionUID = 812345987456321L;

    private String lastname;
    private String firstname;
    private Date date_of_birth;
    private String num_permis;
    private String address;

    /**
     * Construit un objet métier Client en fusionnant :
     *  - les valeurs reçues dans la requête (clientUpdateInput)
     *  - les valeurs déjà existantes en base (alreadySaved)
     *
     *  → Utile pour le PATCH (mise à jour partielle)
     */
    public static Client from(ClientUpdateInput update, Client alreadySaved) {
        return Client.builder()
                .identifier(alreadySaved.getIdentifier()) // jamais modifié
                .lastname(update.getLastname() != null ? update.getLastname() : alreadySaved.getLastname())
                .firstname(update.getFirstname() != null ? update.getFirstname() : alreadySaved.getFirstname())
                .date_of_birth(update.getDate_of_birth() != null ? update.getDate_of_birth() : alreadySaved.getDate_of_birth())
                .num_permis(update.getNum_permis() != null ? update.getNum_permis() : alreadySaved.getNum_permis())
                .address(update.getAddress() != null ? update.getAddress() : alreadySaved.getAddress())
                .build();
    }
}
