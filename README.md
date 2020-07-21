# Challenge-CVC
CVC Challenge, where it is necessary to build an API quickly and have open source resources available, for eventual process failures. 


# Project requirements:
<p>Java 8 or higher. </p>
<p>Docker </p>


# Open source resources

<p> Feign </p>
<p> Hystrix</p>
<p> Kafka</p>
<p> SpringBoot </p>
<p> Lombok</p>
<p> Mongo</p>
<p> WebFlux</p>

# Design Patterns used
<p> Circuit breaker </p>
<p> Builder </p>
<p> Strategy</p>


# First Instruction
<p> Before carrying out the process, have the docker installed on the machine </p>
<p>In this path run the command  <b> docker compose  up -d  </b> It will install the following containers: </p>

<p> <b> mongodb </b> </p>
<p> <b> zookeeper </b> </p>
<p> <b> kafka </b> </p>

<pre>Creating cvcchallenge_zookeeper_1       ... <font color="#4E9A06">done</font>
Creating cvcchallenge_mongodb_container_1 ... <font color="#4E9A06">done</font>
Creating cvcchallenge_kafka_1             ... <font color="#4E9A06">done</font>
</pre>

# Second statement
<p> After finish the containers installations </p>
<p> Access the Micro Service  <b> cvcconsumer </b>  run <b> mvn clean install </b> </p>
<p>after  run  <b> docker build -t cvc/consumer  .  </b>  The micro service will start, on port 8084, so that it can be heard by the fallback api </p>
  
# Third statement
<p> Access the API   <b> cvctest </b> run <b> mvn clean install </b> </p>
<p> after run  <b> docker build -t cvc/cvctest  .  </b>  The API  will start, on port 8080 </p>
<p> Access the Swagger <b> http://localhost:8080/swagger-ui.html </b> </p>  
  

<p> POST  /hotel/v1/consult/findAllHotelsByCityId  </p>
<p> POST  /hotel/v1/consult/findHotelById  </p>

<p> The JSON below will be used to consult for many hotels, or for a specific hotel. </p>

<p> <b> Where the ID will be for all hotels -> City ID  </b> </p> 
<p> <b> Where the ID will be a hotel ID -> Hotel ID     </b> </p>

  
```json
     {
      "checkInDate": "20/05/2017",
      "checkOutDate": "25/05/2017",
      "id": "1032",
      "numberOfAdults": 2,
      "numberOfChildren": 1
     }
```


# Project Architecture
  
  <h1> CACHE </h1>
  
<p> In this challenge, a first-level cache concept was used, that is, a cache for spring itself to manage. </p>

<p> For each call made in the API, to consult the daily rates, differentiation criteria are used, that is, if I increase the number of quantities of an adult or child, or change any value of the payload, a new value is added to the cache. </p>




<h1> Messaging </h1>
  
  
<p> For each value, different than the API, is requested, this value is converted into a builder and played in a queue, so that this information is saved in real time and fetched in real time, in case of an external API failure. </p>

<p> Based on this, we are using a simple example of how Kafka or any other type of messaging can be useful for this type of work and make our application increasingly tolerable to failures. <p/>



<h1> Circuit Breaker </h1>
  
 <p> More and more, we need to be aware of how our API behaves in environments that have high performance, so to avoid any timeout connection problem, lack of response or even downtime of the external API, we use the Hystris feature. <p/>

<p> Based on that, we have a number of features reactive or not with a fallback call. </p>

<h1> WebFlux </h1>
  
<p> We all know how the MVC architecture is totally blocking, so for hystrix fallback calls, we will use spring's webflux feature, to prevent threads from getting stuck in the circuit. </p> 
 
 
  
  
