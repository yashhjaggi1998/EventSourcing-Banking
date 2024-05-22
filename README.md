# Overview
===============================

This application is built to support a simple bank ledger system that utilizes the [EVENT SOURCING](https://martinfowler.com/eaaDev/EventSourcing.html) pattern to maintain a transaction history. We've implemented the event sourcing pattern to record all banking transactions as immutable events. Each event captures relevant information such as transaction type, amount, timestamp, and account identifier.

We are use a SERVICE TO SCHEMA file named service.yml to load API request and response schema as well the necessary modelsof the load and remove API's


## Testing:

All the unit, integration and end-to-end test cases are covered in the test folder.

************************************************************************************
## Local Run instructions are as follows:

1) Load the project in the latest intellij IDE.
2) Install Java jdk 17 or above if not already installed.
3) Install maven if not already installed.
4) Run the following command in the terminal to build the application: $mvn clean install
5) Run the application using the run button in intellij app.
6) To test if the server works, send a GET request to http://localhost:8080/ping. It should return the current time.
7) To run the load api, send a PUT request to http://localhost:8080/load. Set Content-type = "application/json" in the headers of the request. Send the request body as mentioned in the service.yml file. Expect a json response.
7) To run the authorization api, send a PUT request to http://localhost:8080/authorization. Set Content-type = "application/json" in the headers of the request. Send the request body as mentioned in the service.yml file. Expect a json response.

************************************************************************************
## Design considerations

1) Both the api calls read the request parameters and check for various conditions before having to deal with user balance. The reason, is that only if the data is right, can the transaction take place. So, if any of these checks fail, I return the Server Error as a response.

2) If there is an issue with jsut the transaction amount like negative amount or insufficient amount, the API call is not errored but it is logically flawed. FOr this reason, I decided to return a DECLINED as response code with the respective RESPONSE object.

3) I have decided to use a List of Transaction object as an Event aggregator pertaining to Event Sourcing Pattern. So, whenever, the 2 API calls are made their event handlers are invoked and they take care of adding these events to the List. The reason for maintaining a common object is that both requests have similar structure and it helps maintain a much cleaner and structured log of data.

4) I have also decided to replay all transactions after each API call to verify that the history is in sync with current event call. Thus, it will help in finding errorneous transactions easily. The output can be seen in the terminal of intellij.

************************************************************************************
## Assumptions

1) The in-memory List objects are used as a replacement for a database. 
2) message_id field is being considered as the primary key when maintaining transaction logs adhering to the event sourcing design pattern. Thus, it is not being checked if message_id is empty or not in the request, because if it is then it will be assigned an incremental automatically in say databases like SQL.

************************************************************************************
## Bonus: Deployment considerations

1) Considering the application using this API is large, I will dockerize my API callls.
2) Build docker images of them and host it on AWS ECR.
3) Connect to the database and ensure the connectivity works.
4) Configure load balancers to deal with high number of requests.
5) Implement basic authorization or OZAuth authorization to restrict access to only authorized users.
