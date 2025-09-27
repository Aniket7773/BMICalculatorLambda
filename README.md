# BMI Calculator Lambda Service

This is an AWS Lambda service that calculates Body Mass Index (BMI) based on height and weight inputs. The service provides accurate BMI calculations and categorization following WHO standards.

## Project Structure

```
.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── bmi/
│   │   │           └── calculator/
│   │   │               ├── BMICalculatorHandler.java
│   │   │               ├── BMIRequest.java
│   │   │               └── BMIResponse.java
│   │   └── resources/
│   │       └── logback.xml
│   └── test/
│       └── java/
│           └── com/
│               └── bmi/
│                   └── calculator/
│                       └── BMICalculatorHandlerTest.java
└── pom.xml
```

## Features

- Calculates BMI based on height (in centimeters) and weight (in kilograms)
- Categorizes BMI into: Underweight, Normal weight, Overweight, and Obese
- Includes comprehensive error handling
- Provides detailed logging
- Includes complete unit test coverage

## Build Instructions

To build the project:

```bash
mvn clean package
```

This will create a JAR file in the `target` directory that can be deployed to AWS Lambda.

## Testing

To run the tests:

```bash
mvn test
```

## AWS Lambda Configuration

When deploying to AWS Lambda, use the following configuration:

- Runtime: Java 11
- Handler: com.bmi.calculator.BMICalculatorHandler::handleRequest
- Memory: 512 MB (adjustable based on needs)
- Timeout: 30 seconds

## Input Format

The Lambda function expects a JSON input in the following format:

```json
{
    "height": 170,  // Height in centimeters
    "weight": 70    // Weight in kilograms
}
```

## Output Format

The function returns JSON in the following format:

```json
{
    "bmi": "24.22",
    "category": "Normal weight"
}
```

Or in case of an error:

```json
{
    "error": "Error message here"
}
```

## Error Handling

The service handles the following error cases:
- Null request
- Invalid height (zero or negative)
- Invalid weight (zero or negative)
- Internal processing errors

## Logging

The service uses SLF4J with Logback for logging. All logs are written to CloudWatch Logs when deployed to AWS Lambda.