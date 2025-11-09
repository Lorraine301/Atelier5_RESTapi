# Atelier 5 : Mise en place des web services basés sur REST API 

## Gestion des Stations et Carburants

## Description
Cette application permet de gérer des **stations-service**, leurs **carburants disponibles** et les **prix journaliers**. Elle offre des fonctionnalités pour **ajouter, modifier et supprimer** des stations ainsi que leurs carburants et prix.

---

## Fonctionnalités principales

### Stations
- Ajouter une nouvelle station
- Modifier une station existante
- Supprimer une station
- Consulter les détails d’une station

### Carburants
- Ajouter un carburant à une station avec son prix du jour
- Modifier le prix d’un carburant existant
- Supprimer un carburant d’une station
- Consulter l’historique des prix des carburants

---

## Technologies utilisées
- **Frontend** : Angular  
- **Backend** : Spring Boot + REST API  
- **Base de données** : MySQL  
- **Communication** : HTTP REST  

---

## Installation et exécution

### Cloner le projet
```bash
git clone https://github.com/Lorraine301/Atelier5_RESTapi.git

```
### Backend

Ouvrir le projet Spring Boot dans votre IDE (IntelliJ, Eclipse…).

Configurer la base de données dans application.properties.

Lancer l’application Spring Boot.

L’API sera disponible sur http://localhost:8080/api.

### Frontend

Ouvrir le projet Angular.

Installer les dépendances :

```bash
npm install
```

Lancer l’application :

```bash
ng serve
```

Accéder à l’application sur http://localhost:4200.

### Utilisation

- Accéder à la liste des stations.

- Sélectionner une station pour consulter ses détails et les carburants disponibles.

- Ajouter, modifier ou supprimer une station ou un carburant selon les besoins.

Les changements sont automatiquement mis à jour et sauvegardés dans la base de données via les API REST.

## Structure du projet

### Backend

model/ : entités Station, Carburant, HistoCarb

repository/ : interfaces JPA pour l’accès aux données

service/ : logique métier et gestion des historiques de carburants

controller/ : API REST pour exposer les fonctionnalités

### Frontend

station-details.component.ts/html/css : affichage et gestion des stations et carburants

Services Angular pour communiquer avec le backend (HttpClient)

## Auteur

RAHELIARISOA Andriamasy Lorraine Agnès

Deuxième année cycle ingénieur – Filière Logiciels et Systèmes Intelligents
