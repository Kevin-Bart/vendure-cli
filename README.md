### Présentation Générale du Fonctionnement

Ce projet implémente une interface en ligne de commande (CLI) agissant comme un client applicatif pour l'API GraphQL d'une instance e-commerce Vendure. Il permet d'interroger la base de données de la boutique en arrière-plan et de restituer les informations de manière structurée directement dans le terminal, offrant ainsi une alternative programmatique et rapide à la navigation web classique.

#### Flux d'exécution détaillé

Lorsqu'une commande telle que `list` est exécutée dans le terminal, le système suit le processus suivant :

1. **Analyse et traitement de la commande (`CliApp.java` & `ListCommand.java`) :**
L'application intercepte la saisie de l'utilisateur. Elle identifie la commande `list` et analyse les arguments optionnels fournis, tels que le format de sortie souhaité (par exemple, `--format json`).
2. **Préparation de la requête (`GraphQLRequest.java`) :**
Le composant instancie une requête GraphQL spécifique visant à extraire le nom des produits du catalogue, ainsi que le prix de leurs variantes associées.
3. **Transmission des données (`VendureService.java`) :**
La requête GraphQL est encapsulée au format JSON, puis envoyée au serveur via une requête HTTP POST ciblant le point d'accès de l'API Vendure (configuré par défaut sur `http://localhost:3000/shop-api`).
4. **Désérialisation de la réponse (`Product.java`) :**
En retour, le serveur fournit un flux JSON contenant les données extraites. La bibliothèque externe Jackson est exploitée pour analyser ces données brutes et les projeter dynamiquement dans des instances de classes Java (`Product`), garantissant un typage fort pour la suite des opérations.
5. **Restitution à l'utilisateur (`ListCommand.java`) :**
Enfin, le programme itère sur la collection de produits obtenus pour générer l'affichage final. Selon les options définies en amont, le rendu prendra la forme d'un tableau ASCII formaté ou d'une structure de données JSON indentée.

#### Rôle des composants annexes

* **Le répertoire `src/test/` :** Ce dossier centralise l'ensemble des tests unitaires. Ces scénarios permettent de valider isolément le comportement de la logique métier, notamment les mécanismes de sérialisation et de désérialisation, sans nécessiter de connexion active au serveur Vendure.
* **Le fichier `.github/workflows/ci.yml` :** Il définit le pipeline d'Intégration Continue (CI) du projet. À chaque modification poussée sur le dépôt distant, ce composant automatise l'exécution de la suite de tests et vérifie la stricte conformité du code avec les conventions de formatage (Google Java Style), prévenant ainsi toute régression.

#### Cas d'usage concret (Démonstration d'intégration)

Pour illustrer le bon fonctionnement de l'architecture client-serveur de cette application, la procédure suivante peut être exécutée :

1. **Initialisation du serveur :** L'API locale est démarrée en exécutant la commande `npm run dev` au sein du répertoire hébergeant le serveur Vendure (`my-shop`). L'API se met alors en écoute sur l'URL `http://localhost:3000`.
2. **Exécution de la requête cliente :** L'application Java est invoquée via le terminal avec l'argument `list`. L'interface CLI s'exécute, contacte le serveur en arrière-plan, et affiche immédiatement la liste des articles disponibles dans la base de données sous forme de tableau interactif.
3. **Preuve de l'interfaçage dynamique :** Afin de démontrer la communication en temps réel, l'utilisateur peut se connecter au tableau de bord d'administration (`http://localhost:3000/admin`), modifier le nom ou le tarif d'un article, puis valider les changements. Une nouvelle exécution de la commande `list` dans le client Java reflétera instantanément ces modifications, confirmant ainsi l'intégration de bout en bout entre l'interface CLI et le backend Vendure.
