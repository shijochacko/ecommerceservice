## E-commerce Service
This is simplified implementation of e-commerce API, that performs a checkout operation, The checkout end point takes a list of product and return total cost.



## Building the application

The solution is build using Java Spring, In order to run this application, you need to install java on your machine and set java home.


1. Clone the repository to your local machine
```git clone https://github.com/shijochacko/ecommerceservice.git```

2. Navigate to the source directory

3. Clean and compile the solution using gradle
```./gradlew clean```
```./gradlew build```

4. Start the application using
```./gradlew bootRun```
To run the individual test cases, use the below command
```./gradlew test <testcasename>```

## Running the Application

1. Authenticate your self
Currently for the sample implementation, a pre-defined user been defined.

### Basic Authentication
```
curl --location 'http://localhost:8080/user' \--header 'Authorization: Basic <base64 encoded username:password>'
```   

Fetch the authorization header from response object.

To verify the application endpoints use below curl request 
```
curl --location 'http://localhost:8080/api/v1/checkout' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <bearer token>' \
--data '["0001"]'
```

## Approach used to develop application

Test Driven Software Development methodology was followed.

The problem was approached by creating a `Product` model class to hold product information.
From H2 Database, and a checkout service to perform the checkout operation.
The checkout method calculates the total cost of the products, Taking into account any applicable discount using 
price engine service.

In order to improve application performance, we have 
introduced a bare mininum caching layer, which cache 
the data during the initial boot up.

## Improvements

1. For seamless integration CI/CD pipelines can be implemented using jenkins, travis or GitHub actions.
2. Security Implementation can be enhanced with Tokens or Certificate based authentication.
3. Data re-modeling for better product price calculation strategies.
4. Swagger documentation
6. Test Containers for integration testcases.
7. Versioning











