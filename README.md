# API commentaire
API de gestion de commentaire pour l'application de gestion d'évènements.

## Lancer le projet
Pour lancer le projet, il faut d'abord que mongodb soit build et run avec docker : 

On doit créer un réseau local docker : 
```bash
docker network create -d bridge ubo_event_flow
```

Puis on build et run le container mongodb : 
```bash
docker run -d --name mongodb -p 27017:27017 -v C:\chemin\vers\lequel\savelabdd --network ubo_event_flow mongo
```
ou
```bash
./dockerMongodb/run_database_demo.sh
```

Ensuite, on peut build et run le projet : 
```bash
./build_and_run.sh
```
