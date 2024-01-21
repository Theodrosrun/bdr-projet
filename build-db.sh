#!/bin/bash

# Nom du conteneur PostgreSQL
CONTAINER_NAME="postgresql"

# Nom d'utilisateur, mot de passe et nom de la base de données PostgreSQL
DB_USER="bdr"
DB_PASSWORD="bdr"
DB_NAME="bdr"

# Démarrer les conteneurs en arrière-plan
docker compose up -d

sleep 5

# Attendre que le conteneur PostgreSQL soit en cours d'exécution
while [[ "$(docker container inspect -f '{{.State.Status}}' $CONTAINER_NAME 2>/dev/null)" != "running" ]]; do
    sleep 1
done

# Exécute import_schema.sql dans le conteneur PostgreSQL
docker-compose exec -T "$CONTAINER_NAME" psql -U "$DB_USER" -d "$DB_NAME" -a -f "/tmp/sql/import_schema.sql"

# Exécute fill_tables.sql dans le conteneur PostgreSQL
docker-compose exec -T "$CONTAINER_NAME" psql -U "$DB_USER" -d "$DB_NAME" -a -f "/tmp/sql/fill_tables.sql"

# Arrêter un conteneur nommé "myamazingfitness"
docker container kill myamazingfitness

# Redémarrer un conteneur nommé "myamazingfitness"
docker container start myamazingfitness
