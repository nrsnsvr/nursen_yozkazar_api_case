This project provides testing for Pet Store API reliability and performance.

## Test Endpoints

- **POST /pet** - Create pet
- **GET /pet/{id}** - Get pet information
- **PUT /pet** - Update pet
- **DELETE /pet/{id}** - Delete pet  
- **GET /pet/findByStatus** - Find pets by status

## Test Execution

### Run All Tests
mvn test

### Run Tests in Parallel
mvn test -DsuiteXmlFile=regression.xml

## Test Reporting

If you want to generate test report via Allure:
allure serve target/allure-results
