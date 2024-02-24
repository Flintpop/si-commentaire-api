# To-Do List des Contraintes à Tester

## Contraintes sur les Membres
- [ ] Un membre ne peut pas avoir le même nom et prénom qu'un autre membre existant.

## Contraintes sur les Événements
- [ ] Deux événements ne peuvent pas avoir lieu en même temps dans le même lieu.
- [ ] La capacité du lieu doit être suffisante pour accueillir tous les participants d'un événement.
- [ ] Un événement ne peut être créé si la date est passée.

## Contraintes sur les Inscriptions
- [ ] Un membre ne peut pas s'inscrire à un événement s'il est déjà inscrit à un autre événement qui se chevauche dans le temps.
- [ ] Un membre ne peut s'inscrire à un événement si le nombre maximal de participants est atteint.
- [ ] Un membre peut se désinscrire d’un événement seulement si l'événement n'a pas encore eu lieu.

## Contraintes sur les Lieux
- [ ] Un lieu doit avoir une capacité définie (nombre maximum de personnes).

## Contraintes sur les Commentaires
- [ ] Les membres doivent pouvoir déposer des commentairePojos sur les événements.

## Fonctionnalités et Accès aux Données
- [ ] Il doit être possible de visualiser l’ensemble des membres de l’association.
- [ ] Il doit être possible de visualiser l’ensemble des événements, filtrables par tous ou ceux à venir.
- [ ] Il doit être possible de visualiser l’ensemble des inscriptions pour un événement donné, incluant le nombre d’inscrits.
- [ ] Chaque membre doit pouvoir lister les événements auxquels il est inscrit, filtrables par tous ou ceux à venir.
- [ ] Il doit être possible de créer, modifier et supprimer des membres ou des événements.
- [ ] Il doit être possible d’afficher les événements par lieu, incluant une carte d’accès au lieu via l’API d’OpenStreetMap.

## Intégration et Services
- [ ] Assurer que l'accès aux données de la base SQL se fait correctement via des entités JPA.
- [ ] Les commentairePojos doivent être correctement stockés et récupérés de la base de données MongoDB.
- [ ] L'interface client Web développée avec Vue.js doit permettre la modification et l'accès aux informations de manière fluide et sécurisée.
- [ ] Les API REST développées doivent répondre correctement aux requêtes, respectant les contraintes métier et de sécurité.

