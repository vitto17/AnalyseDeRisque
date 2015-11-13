Avant de pouvoir lancer vos applications de formation, vous avez besoin d'une 
instance de HSQLDB en mode serveur. Pour cela, HSQLDB et un script de lancement 
sont intégrés au projet formation-orion dans le module Db-formation:

- Avec une console (windows, linux ou cygwin), positionnez vous dans le 
  répertoire db à la racine du projet Db-formation.
- En fonction de votre système d'exploitation, exécutez soit run-hsql.bat soit run-hsql.sh. Vérifiez que les droits d'éxecution sont bien activés sur le fichier.
- Déployer l'application de formation "webapp-formation" pour initialiser le schéma de la BD.
- A l'aide du service d'administration des bases de données fournie par netbeans (Onglet Services / Databases), créez une nouvelle connexion HSQLDB. Pour cela 
  ouvrez l'assistant de création d'une nouvelle connexion :
    -- Choisir dans la combobox Driver la valeur "New Driver" et ajouter le driver "hsqldb-2.3.1.jar"
       qui se trouve sous le chemin "Db-formation/db/lib" à l'aide du bouton "Add..." .
    -- Dans la deuxième page de l'assistant, donner un nom au driver.
    -- Entrer dans la partie "User Name" le login "sa" et laisser la partie password vide.
    -- Entrer dans la partie "JDBC URL" l'url de la BD "jdbc:hsqldb:hsql://localhost/demo".

- Pour ajouter les données dans la base HSQLDB, il faut exécuter les scripts : Init_webapp_data.sql et Init_webmin_data.sql qui se trouvent sous le chemin "Db-formation/db/script-sql"