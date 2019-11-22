# BILLING

Fast, reliable and lightweight billing manager. Designed to manage the facturation process and work with the most basics functionality of billing and this project was developed.

### Configurations

As the project was built with using _Spring_  you can import this from eclipse or IntelliJ as a **maven** project to your solution. This project is running with Java 8, so be sure you have downloaded jdk1.8 into the machine.

### Usage

To run the project you can clone the repository using the following command:
```
$ git clone https://github.com/arielmorel/billing
```

### Project Structure

The project was designed with the TDD methodology and folder structure is defined as follow:

com.arielmorel.billing

### Documentation

To see the documentation you can run the project and see the next Uri:
```
http://localhost:8080/swagger-ui.html#
```

### Functions

This section contain the functions that can be called in this application, which is the main purpose of this application. Find in the table below which functionality you want to use: 

 Function   |      Parameter      |  Type |  Description 
------------|---------------------|-------|---------------
| createProduct |  Product  | Object | Create a product |
| updateProduct |  Product  | Object | update a product |
| getAllProducts |    |  | return a list of products |
| createCustomer |  Customer  | Object | Create a Customer |
| findAll |    |  | return a list of customers |
| placeOrder |  Order,OrderDetail  | Object, List | Create a order |
| getOrderByPeriod |  startDate,endDate  | Date format(yyyy-MM-dd) , Date format(yyyy-MM-dd)|  return a list of order by start and end date  |


### Project Requirements

1. Create a tiny REST / JSON web service in Java using Spring Boot (RestController) with an API that
supports basic products CRUD:
    ● Create a new product
    ● Get a list of all products
    ● Update a product
2. The API should also support:
    ● Placing an order
    ● Retrieving all orders within a given time period
3. Provide unit tests.