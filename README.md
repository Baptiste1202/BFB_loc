# BFB Rental

Application de gestion de location de véhicules développée avec Spring Boot et MongoDB.

## Description

Ce projet implémente un système de location de véhicules permettant de gérer des clients, des véhicules (voitures et camions) et des contrats de location. L'architecture suit les principes de la clean architecture avec une architecture en couchess : séparation claire entre les couches métier, infrastructure et interface. On utilise aussi des design patterns pour assurer une architecture maintenable et extensible. 

## Technologies

- **Backend** : Spring Boot 3.5.7
- **Base de données** : MongoDB
- **Build** : Maven
- **Documentation API** : Swagger/OpenAPI

## Architecture

Le projet est organisé en trois couches principales :

- `business` : Logique métier (services, modèles, stratégies de pricing)
- `infrastructures` : Accès aux données (repositories, entities, mappers)
- `interfaces` : Contrôleurs REST et DTOs

### Fonctionnalités principales

- Gestion des clients
- Gestion des véhicules (voitures et camions)
- Gestion des contrats de location
- Calcul de prix avec différentes stratégies (basique, réduction progressive, saisonnière, hebdomadaire)
- Validation des données

## Design Patterns

### Chain of Responsibility (Validateurs)

Le pattern Chain of Responsibility est utilisé pour la validation des contrats. Chaque validateur vérifie un aspect spécifique et passe la main au suivant dans la chaîne.

**Implémentation** :
- `Validator<T>` : Interface définissant le contrat de validation
- `BaseValidator<T>` : Classe abstraite gérant le chaînage avec `setNext()` et `validateNext()`
- Validateurs concrets : `ClientValidator`, `VehiculeValidator`, `LengthValidator`

**Avantages** :
- Séparation des responsabilités de validation
- Ajout facile de nouvelles règles de validation
- Ordre d'exécution configurable

### Strategy + Factory (Calcul de prix)

Le pattern Strategy permet de définir différents algorithmes de calcul de prix, tandis que le Factory Pattern facilite leur instanciation.

**Implémentation** :
- `PricingStrategy` : Interface définissant `calculatePrice(Contrat)`
- Stratégies concrètes :
  - `BasicPricingStrategy` : Calcul simple (prix journalier × nombre de jours)
  - `WeeklyDiscountPricingStrategy` : Réduction pour les locations hebdomadaires
  - `SeasonalPricingStrategy` : Prix variables selon la saison
  - `ProgressiveDiscountPricingStrategy` : Réduction progressive selon la durée
- `PricingStrategyFactory` : Crée les instances de stratégies à la demande

**Avantages** :
- Changement de stratégie de pricing à l'exécution
- Ajout de nouvelles stratégies sans modifier le code existant
- Centralisation de la création des stratégies

### Mapper Pattern

Le pattern Mapper assure la conversion entre les différentes représentations des données (entités BDD, modèles métier, DTOs).

**Implémentation** :
- `AbstractBddMapper<T, E>` : Classe abstraite définissant `from(E)` et `to(T)`
- Mappers concrets : `ClientBddMapper`, `ContratBddMapper`, `VehiculeBddMapper`
- Séparation entre les entités MongoDB et les objets métier

**Avantages** :
- Isolation des couches (base de données vs logique métier)
- Facilite les changements de structure de données
- Code de conversion centralisé et réutilisable

## Installation

### Prérequis

- Java 17
- Maven
- Docker (pour MongoDB)

### Lancer MongoDB

```bash
docker run -d -p 27017:27017 --name mongodb mongo
```

### Lancer l'application

```bash
./mvnw spring-boot:run
```

L'application sera accessible sur `http://localhost:8080`

## Documentation API

Une fois l'application lancée, la documentation Swagger est disponible à l'adresse :

```
http://localhost:8080/swagger-ui.html
```

## Endpoints principaux

- `/api/clients` : Gestion des clients
- `/api/vehicules` : Gestion des véhicules
- `/api/contrats` : Gestion des contrats de location

## Configuration

La configuration se trouve dans `application.properties` :
- Port serveur : 8080
- Base de données : bfb_rental (MongoDB local) 

## Auteurs

Projet réalisé par Mikaël Vivier et Baptiste Bertrand

