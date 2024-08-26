# Monolith to Microservices

Welkom bij het **Monolith to Microservices** project! Dit project heeft als doel een bestaande monolithische applicatie
om te zetten naar een microservices-architectuur. Door gebruik te maken van moderne technologieën en best practices, biedt
dit project een hands-on ervaring met het opsplitsen van een monoliet en het implementeren van microservices.

## Inhoudsopgave

- [Setup](#setup)
  - [Docker](#docker)
- [Uitleg applicatie](#uitleg-applicatie)
  - [Functionaliteiten](#functionaliteiten)
  - [Hexagonale architectuur](#hexagonale-architectuur)
  - [Spring Boot dependencies](#spring-boot-dependencies)
  - [Configuratie](#configuratie)
- [Microservices](#microservices)

## Setup

Dit project maakt gebruik van de volgende technologieën en tools:

- **Java 21**
- **Docker**: [Installeer hier](https://www.docker.com/products/docker-desktop)
- **IDE naar keuze**: ik maak gebruik van [IntelliJ IDEA](https://www.jetbrains.com/idea/)

Clone deze repository en open het in je IDE.

```shell
git clone https://github.com/jochemdeschepper/monolith-to-microservices.git
```

### Docker

Zorg ervoor dat Docker desktop aan het runnen is en navigeer naar de root van het project in de terminal 
voer het volgende commando uit:

```shell
docker compose up -d
```

Dit commando start een Postgres-container met de volgende inloggegevens:

- **Gebruikersnaam**: postgres
- **Wachtwoord**: postgres
- **Database**: postgres

**Opmerking**: De databaseconfiguratie is te vinden in het `application.yml` bestand in de `src/main/resources` folder.

## Uitleg applicatie

De applicatie, een REST API voor een webshop, vormt de backend van een e-commerce site waar diverse producten worden verkocht.

De applicatie is ontworpen met behulp van Spring Boot, een populair framework voor het bouwen van Java-gebaseerde webapplicaties.

### Functionaliteiten

- **Gebruikersbeheer**: Het registreren en inloggen van gebruikers.
- **Productbeheer**: Het opvragen van producten.
- **Winkelmandbeheer**: Het toevoegen en updaten van producten aan een persoonlijke winkelmand en het bekijken van de inhoud ervan.
- **Orderverwerking**: Het plaatsen van een bestelling van de producten in de winkel-mand en het opvragen van de geplaatste bestellingen.

### Hexagonale architectuur

De applicatie maakt gebruik van een hexagonale architectuur, ook wel bekend als de "Ports and Adapters" architectuur. 
Deze architectuur verdeelt de applicatie in verschillende lagen:

- **Domain**: De kern van de applicatie, waar de business logica en entiteiten zich bevinden.
- **Application**: De laag die de business logica van de applicatie bevat.
- **Infrastructure**: De laag die de applicatie verbindt met externe services en databases.

### Spring Boot dependencies

De applicatie maakt gebruik van de volgende Spring Boot dependencies:

- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Flyway Migration
- Validation
- OpenAPI (Swagger documentatie)

### Configuratie

Alle configuratie van de applicatie is te vinden in het `application.yml` bestand in de `src/main/resources` folder.

## Microservices

Uit het documentatierapport is gebleken dat volgende microservices kunnen worden geïmplementeerd:

- Product
- Cart
- User
- Order

Omdat de product service het minst afhankelijk is van de andere services, beginnen we met het opsplitsen van de product service.

Ga naar de product-service repository om de product-service verder uit te werken: [Product Service](https://github.com/jochemdeschepper/monolith-to-microservices-product-service)
