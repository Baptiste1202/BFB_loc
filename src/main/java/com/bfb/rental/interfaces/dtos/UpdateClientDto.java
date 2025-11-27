package com.bfb.rental.interfaces.dtos;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import com.bfb.rental.business.clients.model.Client;
import java.time.LocalDate;

/**
 * JUSTIFICATION : Ajouter une méthode merge
 *
 * Pattern du controller : UpdateClientDto.merge(input, existing)
 * Fusionner les données de modification avec l'entité existante
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClientDto {

    @Size(min = 2, max = 100)
    private String nom;

    @Size(min = 2, max = 100)
    private String prenom;

    @Past
    private LocalDate dateNaissance;

    @Pattern(regexp = "^[A-Z0-9]{8,15}$|^$")
    private String numPermis;

    @Size(min = 5, max = 255)
    private String adresse;

    /**
     * Fusionner les champs modifiés avec l'entité existante
     */
    public static Client merge(UpdateClientDto dto, Client existing) {
        if (dto.nom != null) existing.setNom(dto.nom);
        if (dto.prenom != null) existing.setPrenom(dto.prenom);
        if (dto.dateNaissance != null) existing.setDateNaissance(dto.dateNaissance);
        if (dto.numPermis != null) existing.setNumPermis(dto.numPermis);
        if (dto.adresse != null) existing.setAdresse(dto.adresse);

        return existing;
    }
}
