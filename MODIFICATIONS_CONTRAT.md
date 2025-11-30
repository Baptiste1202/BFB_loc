# Modifications apportées au modèle Contrat

## Objectif
Remplacer les identifiants client et véhicule (UUID) par les objets complets (Client et TransportVehicle) dans la classe Contrat.

## Fichiers modifiés

### 1. `src/main/java/com/bfb/rental/business/contrats/model/Contrat.java`
**Changements:**
- Remplacement de `private UUID clientId` par `private Client client`
- Remplacement de `private UUID vehiculeId` par `private TransportVehicle vehicule`
- Ajout d'imports pour Client et TransportVehicle
- Mise à jour des constructeurs pour utiliser les objets au lieu des IDs
- Remplacement des getters/setters:
  - `getClientId()` → `getClient()`
  - `getVehiculeId()` → `getVehicule()`
  - `setClientId()` → `setClient()`
  - `setVehiculeId()` → `setVehicule()`

**Avantages:**
- Pas besoin de charger le client/véhicule chaque fois qu'on a besoin de ses propriétés
- Meilleure sémantique du code
- Évite les appels à la base de données supplémentaires

### 2. `src/main/java/com/bfb/rental/infrastructures/bdd/contrats/repositories/mappers/ContratBddMapper.java`
**Changements:**
- Injection des dépendances `ClientsService` et `VehicleService` via `@RequiredArgsConstructor`
- Ajout de `@RequiredArgsConstructor` et utilisation de `@Component` existant
- Dans la méthode `from()`: Chargement du Client et TransportVehicle depuis leurs IDs avant la construction du Contrat
- Dans la méthode `to()`: Extraction des IDs depuis les objets Client et Vehicule lors de la conversion

**Approche:**
- Stockage des IDs en base de données (pas de changement)
- Conversion automatique lors du mapping entre entité BDD et modèle métier
- Gestion des erreurs si le client ou le véhicule n'existe pas

### 3. `src/main/java/com/bfb/rental/business/vehicles/pricing/PricingService.java`
**Changements:**
- Suppression de l'injection de `VehicleService` (n'est plus nécessaire)
- Suppression de l'import `java.util.Optional` (inutilisé)
- Modification de `calculateContractPrice(Contrat, String)`:
  - Ancienne approche: `vehicleService.getOne(contrat.getVehiculeId())`
  - Nouvelle approche: Utilisation directe de `contrat.getVehicule()`
- Modification de `calculateDefaultContractPrice(Contrat)`:
  - Même changement

**Bénéfices:**
- Réduction des dépendances circulaires
- Performance: Pas d'appel réseau/BDD supplémentaire
- Simplification du code

### 4. `src/main/java/com/bfb/rental/business/contrats/ContratsService.java`
**Changements:**
- Injection des dépendances `ClientsService` et `VehicleService`
- Ajout d'une nouvelle méthode `createFromDto(CreateContratDto dto)`
- Imports additionnels: `LocalDateTime`, `ClientsService`, `CreateContratDto`, `VehicleService`

**Nouvelle méthode `createFromDto()`:**
```java
public Contrat createFromDto(final CreateContratDto dto) {
    var client = clientsService.getOne(dto.getClientId())
            .orElseThrow(() -> new IllegalArgumentException("Client non trouvé..."));
    var vehicule = vehicleService.getOne(dto.getVehiculeId())
            .orElseThrow(() -> new IllegalArgumentException("Véhicule non trouvé..."));
    
    Contrat contrat = Contrat.builder()
            .id(UUID.randomUUID())
            .client(client)
            .vehicule(vehicule)
            .dateDebut(dto.getDateDebut())
            .dateFin(dto.getDateFin())
            .dateCreation(LocalDateTime.now())
            .dateModification(LocalDateTime.now())
            .build();
    
    return this.create(contrat);
}
```

**Utilisation:**
- Permet de créer un Contrat à partir d'un DTO reçu d'une API REST
- Le DTO garde les IDs (ce qui est approprié pour l'interface externe)
- Le service enrichit les données en chargeant les objets complets

## Impact sur les autres classes

### Pas de changement requis
- **CreateContratDto**: Reste inchangé (garde les IDs)
- **UpdateContratDto**: Reste inchangé (ne manipule pas les IDs)
- **PricingStrategy** et ses implémentations: Compatibles (utilisent dateDebut, dateFin du contrat)
- **ContratEntity**: Reste inchangé (toujours stockée avec les IDs en BDD)
- **ContratsService.create()**: Reste compatible (crée toujours le contrat)

## Tests recommandés

1. Tester le chargement d'un contrat depuis la BDD (mapping à travers ContratBddMapper)
2. Tester la création d'un contrat via `createFromDto()`
3. Tester les stratégies de pricing pour vérifier qu'elles utilisent bien le véhicule du contrat
4. Tester l'update d'un contrat
5. Vérifier que les calculs de prix fonctionnent correctement

## Notes de conception

La modification respecte les principes SOLID:
- **Single Responsibility**: Chaque classe a une responsabilité unique
- **Open/Closed**: Les stratégies de pricing ne changent pas
- **Dependency Inversion**: Utilisation de l'injection de dépendances
- **Liskov Substitution**: Les contrats restent substituables

Le modèle de données en BDD ne change pas, seule l'abstraction en mémoire change.
