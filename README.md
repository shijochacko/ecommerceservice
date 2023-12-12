## e-commerceservice
e-commerce service is used for checkout operation.
Used to calculate total price for selected products.


### Ecommerceservice 

How to run this application



#### Clone
git clone https://github.com/shijochacko/ecommerceservice.git

#### To Build and Run using gradle
Move to directory where code cloned using
cd ecommerceservice

#### Build Application
gradle clean build

#### Run Application
gradle bootRun

#### Run test
gradle test

### Approch used to develop application


##### Used Spring boot microservice architecture to develop application
##### Test Driven Development
##### In-memory H2 Database to store data
##### Implemented Cache to improve performance and avoid multiple database hit for same item in cart
##### Created services based on the operations

### Improvements

##### CI/CD pipeline and Git Actions can be implemented
##### Security Implementation can be enhanced with Tokens or Certificate based authentication
##### Data re-modeling for better product price calculation
##### Swagger documentation 
##### Versioning
##### Containeraized testcases











