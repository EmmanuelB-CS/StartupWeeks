CREATE TABLE sport (
    id INT PRIMARY KEY AUTO_INCREMENT,
    intitule TEXT,
    effet TEXT,
    duree TEXT,
    date_entree TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    patient_id INT,
    FOREIGN KEY (patient_id) REFERENCES patient(id) -- cette dernière ligne sera ajoutée quand on aura un système d'authentification
);

/*
Note: tout les colonnes d'entrées sont en TEXT car SendDataToServer Task convertit des strings et cela simplifie donc les routes
qui n'auront pas à faire de conversion selon les tables dans lesquelles elles enregistreront les données.
Cependant il ne faudra pas oublier:
1 - de convertir les données en fonction des usages qu'on en fait (voir la route create_graph où l'on convertit des données en int)
2 - mettre dans l'application java des zones d'entrée qui obligent l'utilisateur à entrer des entiers (car ouverture du clavier numérique)
*/ 