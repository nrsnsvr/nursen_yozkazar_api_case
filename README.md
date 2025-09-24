This project provides testing for Pet Store API reliability and performance.

Test Endpoints

POST /pet - Create pet
GET /pet/{id} - Get pet information
PUT /pet - Update pet
DELETE /pet/{id} - Delete pet
GET /pet/findByStatus - Find pets by status

Run All Tests
bashmvn test
Run Tests in Parallel
bashmvn test -DsuiteXmlFile=regression.xml

If you want to generate test report via Allure:
bashallure serve target/allure-results
