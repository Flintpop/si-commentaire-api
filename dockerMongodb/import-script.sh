#!/bin/bash
# Attendre que MongoDB démarre
echo "Attente du démarrage de MongoDB..."
while ! nc -z localhost 27017; do
  sleep 0.1
done
echo "MongoDB a démarré"

# Importer les données JSON dans MongoDB
mongoimport --uri "mongodb://localhost:27017/commentaires" --collection commentaires --file /usr/src/configs/mongodb_file.json

# Garder le conteneur en vie (MongoDB en mode foreground)
mongod --bind_ip_all
