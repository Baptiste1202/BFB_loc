package com.example.demo.interfaces.clients;

import com.example.demo.business.clients.ClientsService;
import com.example.demo.business.clients.model.Client;
import com.example.demo.interfaces.common.exception.NotFoundException;
import com.example.demo.interfaces.clients.model.input.ClientInput;
import com.example.demo.interfaces.clients.model.input.ClientUpdateInput;
import com.example.demo.interfaces.clients.model.output.ClientOutput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("BFB/v1/clients")
public class ClientsController {

    private final ClientsService clientsService;

    //"APPLICATION_JSON_VALUE" réponse au format json
    //"requireNonNullElse" Si clientsService.getAll() est non nul, on l’utilise. Sinon, on utilise un ensemble vide à la place "emptySet". Évite un NullPointerException.
    // stream() transforme une collection en flux d’éléments pour pouvoir appliquer des opérations fonctionnelles comme map et collect
    //map() applique une transformation à chaque élément du stream
    //collect() On collecte les éléments du stream (transformés par map) dans un Set

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<ClientOutput> getAll() {
        return Objects.requireNonNullElse(this.clientsService.getAll(), Collections.<Client>emptySet()).stream()
                .map(ClientOutput::from)
                .collect(Collectors.toSet());
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientOutput create(@RequestBody final ClientInput client) {
        return ClientOutput.from(
                this.clientsService.create(
                        ClientInput.convert(client)
                )
        );
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientOutput getOne(@PathVariable("idClient") final String identifier) {
        return this.clientsService.getOne(UUID.fromString(identifier))
                .map(ClientOutput::from)
                .orElseThrow(() -> new NotFoundException(String.format("Le client d'identifiant %s n'a pas été trouvé.", identifier)));
    }

    //alreadySaved est le nom de l'objet client récupéré en base avec getOne
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{idClient}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable("idClient") final String identifier, @RequestBody final ClientUpdateInput client) {
        this.clientsService.update(
                this.clientsService.getOne(UUID.fromString(identifier))
                        .map(alreadySaved -> ClientUpdateInput.from(client, alreadySaved))
                        .orElseThrow(() -> new NotFoundException(String.format("Le client d'identifiant %s n'a pas été trouvé.", identifier)))
        );
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{idClient}")
    public void delete(@PathVariable("idClient") final String identifier) {
        this.clientsService.delete(UUID.fromString(identifier));
    }

}
