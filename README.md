# Introduction:

The RestAssured API Testing Framework is a comprehensive solution for automating API testing using Java and REST-Assured. This project aims to provide a robust and scalable framework for validating RESTful APIs, enabling teams to efficiently test and verify the functionality, performance, and reliability of their API endpoints.

# Features:

1. Easy-to-use API testing framework powered by REST-Assured library.
2. Support for writing test cases using TestNG and BDD with Cucumber.
3. Flexible and customizable test configurations for various environments.
4. Detailed reporting and logging capabilities for comprehensive test result analysis.
5. Integration with Maven for seamless dependency management and project build.

# Tools used: 
1. Java
2. REST-Assured
3. TestNG
4. Cucumber
5. Maven
6. IntelliJ IDEA /Eclipse (choice)

## Dependencies:
1. Rest Assured
2. json-path
3. json
4. gson
5. Testng
6. scribejava-api (3rd party api to generate dynamic data for testing)
7. json-schema-validator
8. xml-schema-validator


## Getting Started

Follow these step-by-step instructions to fork the repository and set up the project for initial support.


### Fork the Repository

1. Navigate to the repository on GitHub: https://github.com/MohammedJafferAli/PractiseRestAssured
2. Click on the "Fork" button in the top-right corner of the page.
3. Wait for the repository to be forked to your GitHub account.

### Clone the Forked Repository

1. Open your terminal or command prompt.
2. Use the following command to clone the repository to your local machine:

   Git bash
   git clone https://github.com/your-username/your-repo-name.git

# Open the Project in IntelliJ IDEA

Open IntelliJ IDEA.
Select "Open" from the main menu.
Navigate to the directory where you cloned the repository and select it.
Click "Open" to open the project.
## Set Up Maven Dependencies
This project uses Maven for dependency management. Make sure you have Maven installed on your system. 
If not, you can download it from Maven's official website. https://mvnrepository.com/

# Static imports
In order to use REST assured effectively it's recommended to statically import methods from the following classes:

io.restassured.RestAssured.*

io.restassured.matcher.RestAssuredMatchers.*

org.hamcrest.Matchers.*

If you want to use Json Schema validation you should also statically import these methods:


io.restassured.module.jsv.JsonSchemaValidator.*


