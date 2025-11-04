package com.example.demo.interfaces.clients.model.common.model.input;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serial;
import java.io.Serializable;

/* La classe AbstractInput sert de classe de base pour tous les objets d’entrée (DTO) dans l’architecture REST du projet.
 Elle implémente Serializable pour permettre la conversion des objets en bytes
 L’annotation @JsonIgnoreProperties(ignoreUnknown = true) permet d’ignorer les champs JSON inconnus lors de la désérialisation, évitant ainsi les erreurs.
 Elle définit également un serialVersionUID pour assurer la compatibilité entre versions lors de la sérialisation.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbstractInput implements Serializable {
    @Serial
    private static final long serialVersionUID = -1252322137610918488L;
}