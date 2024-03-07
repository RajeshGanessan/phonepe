# Phonepe Assignment

This project consists of tests which searches product on Amazon.in and Flipkart.in compares the price between the both and outputs the product URL of the minimum priced product 

### Prerequisites
Need to have Java and Maven with any IDE in your machine

### How to use
Import the project in IntelliJ editor.
Go to folder src/test/java/resources/testRunner

Run the xml file "testng.xml"

## Project structure
Hybrid framework with Page object model design pattern.

Added browser selection for chrome, firefox, safari and Edge

### Reporting 
Has Intergration with Extent Report to log the tests 

### src/main/java : 
It contains the classes with functionalities that drives the test cases.
### src/test/java : 
It contains the test suites.
### src/test/resources  : 
Contains testRunner XML 
### testng.xml : 
It facilitates running the test suites all at once from this file.

### How to Run tests

* Run the testng.xml file as Test

* We can run it as a maven project as well, through `mvn clean test` command 
