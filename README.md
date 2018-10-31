# takeaway-challenge

## Run

```
docker network create takeaway
cd coding-challenge
# Edit "volumes" section of takeaway-postgres service in docker-compose.yml file as you prefer.
# Also please make sure your 8080 and 8081 ports are available, if not, please edit "ports" section of takeaway-consumer and takeaway-producer services as you prefer.
docker-compose up -d --build
```
After roughly five to ten seconds of executing docker-compose command, you can access consumer through 8080 port of localhost and producer through 8081 port of localhost as well.

## Test

Please check [.json](https://github.com/mcege/takeaway-challenge/blob/master/coding-challenge/takeaway.postman_collection.json) which had been exported from Postman, includes several GET/POST requests for the testing purposes.

## Architecture

Model-view-controller (MVC) architecture had been used. Both consumer and producer microservices are layered by MVC components, such as controller, service, config, utils, etc. Also in both of the microservices, a Data Transfer Object (DTO) had been used in order to avoid the RabbitMQ queue data being available to be exposed externally. 

## Service Design

Please check the [service diagram](https://github.com/mcege/takeaway-challenge/blob/master/takeaway-service-diagram.png).

## Security Concerns

First of all, the endpoints are insecure, there is no token authentication, or such, neither in body nor in header. Also ideally, the sensitive information such as database authentication information, or message broker queue details should be either vaulted, or read through an environment file, or as an environment variable. For instance, if Helm is preferred to manage the configuration to deploy to Kubernetes clusters, than an additional feature such as Helm-Secrets would handle these sensitive information, or Cloudflare can be a possible solution for that matter as well.

Also, as this was thought to be an internal "test" project, RabbitMQ queues had left available as well. Anyone who accesses those queues, can read those queues. There could be implemented a serializer solution, or an encryption method could be applied for messaged before getting written to the related RabbitMQ queue. 

## Performance Issues

The PostgreSQL had been chosen to get benefit from hibernate, to make read/write processes to be quicker and an another reason to choose PostgreSQL instead of in memory database was to ensure the data to be persistently stored. But, of course as the task had been take care of as a "test" application and the amount of data and traffic had foreseen to be minimal, some further optimization hadn't been implemented. In a production environment, first of all, the database should have been designed to be highly available. The separation of read and write databases would result a better performance as well. The same applies for RabbitMQ as well, it had not been clusterized, so we put too many faith on the state of mind of RabbitMQ. In the current solution, RabbitMQ is exposable to disaster, as well as slow performance, in any t time of too many requests. 

## Prometheus

These links below will up&run roughly in twenty seconds after docker-compose command finishes executing with "success".

[Metrics of Consumer in .json format](http://localhost:8080/actuator/prometheus)<br/>
[Metrics of Producer in .json format](http://localhost:8081/actuator/prometheus)
