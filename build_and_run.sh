mvn clean package
docker build -t si-commentaire-api .
docker run -d --name si-commenataire-api -p 8081:8080 --network ubo_event_flow si-commentaire-api
