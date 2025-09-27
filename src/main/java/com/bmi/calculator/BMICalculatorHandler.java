package com.bmi.calculator;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BMI Calculator Lambda Handler
 * Calculates BMI and categorizes it according to WHO standards:
 * - Underweight: < 18.5
 * - Normal weight: 18.5 - 24.9
 * - Overweight: 25 - 29.9
 * - Obese: ≥ 30
 */
public class BMICalculatorHandler implements RequestHandler<BMIRequest, BMIResponse> {
    private static final Logger logger = LoggerFactory.getLogger(BMICalculatorHandler.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public BMIResponse handleRequest(BMIRequest request, Context context) {
        logger.info("Processing BMI calculation request");
        
        try {
            validateRequest(request);
            
            double heightInMeters = request.getHeight() / 100.0; // Convert cm to meters
            double bmi = calculateBMI(request.getWeight(), heightInMeters);
            String category = determineBMICategory(bmi);
            
            logger.info("BMI calculated successfully: {}", bmi);
            
            return BMIResponse.builder()
                    .bmi(String.format("%.2f", bmi))
                    .category(category)
                    .build();
                    
        } catch (IllegalArgumentException e) {
            logger.error("Invalid input parameters: {}", e.getMessage());
            return BMIResponse.builder()
                    .error("Invalid input: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            logger.error("Error processing request", e);
            return BMIResponse.builder()
                    .error("Internal server error")
                    .build();
        }
    }

    private void validateRequest(BMIRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getHeight() <= 0) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }
        if (request.getWeight() <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }
    }

    private double calculateBMI(double weightInKg, double heightInMeters) {
        return weightInKg / (heightInMeters * heightInMeters);
    }

    private String determineBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal weight";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}