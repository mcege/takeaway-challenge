# takeaway-challenge

## run

```
docker network create takeaway
cd coding-challenge
<!---
Edit volumes section for docker-compose.yml as you prefer.
-->
docker-compose up -d --build
```
You can reach consumer through 8080 port of localhost and producer through 8081 port of localhost as well.

## testing

Please reach [.json](https://github.com/mcege/takeaway-challenge/blob/master/coding-challenge/takeaway.postman_collection.json) which had exported from Postman, includes several GET/POST requests for the testing purposes.

## architecture

Model-view-controller (MVC) architecture had been used. Both consumer and producer microservices are layered by MVC components, such as controller, service, config, utils, etc. Also in both of the microservices, a Data Transfer Object (DTO) had been used in order to avoid the RabbitMQ queue data being available to be exposed externally. 

## service design

Please check the [service diagram](https://github.com/mcege/takeaway-challenge/blob/master/takeaway-service-diagram.png).

## security concerns

First of all, the endpoints are insecure, there is no token authentication, or such, neither in body nor in header. Also ideally, the sensitive information such as database authentication information, or message broker queue details should be either vaulted, or read through an environment file, or as an environment variable. For instance, if Helm is preferred to manage the configuration to deploy to Kubernetes clusters, than an additional feature such as Helm-Secrets would handle these sensitive information, or Cloudflare can be a possible solution for that matter as well.

Also, as this was thought to be an internal "test" project, RabbitMQ queues had left available as well. Anyone who accesses those queues, can read those queues. There could be implemented a serializer solution, or an encryption method could be applied for messaged before getting written to the related RabbitMQ queue. 

## performance issues

The PostgreSQL had been chosen to get benefit from hibernate, to make read/write processes to be quicker and an another reason to choose PostgreSQL instead of in memory database was to ensure the data to be persistently stored. But, of course as the task had been take care of as a "test" application and the amount of data and traffic had foreseen to be minimal, some further optimization hadn't been implemented. In a production environment, first of all, the database should have been designed to be highly available. The separation of read and write databases would result a better performance as well. The same applies for RabbitMQ as well, it had not been clusterized, so we put too many faith on the state of mind of RabbitMQ. In the current solution, RabbitMQ is exposable to disaster, as well as slow performance, in any t time of too many requests. 
