package com.bmi.calculator;

im        BMIRequest request = new BMIRequest(170, 70); // 170cm, 70kg
        BMIResponse response = handler.calculateBMI(request);rt org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BMICalculatorHandlerTest {

    private BMICalculatorHandler handler;

    @BeforeEach
    void setUp() {
        handler = new BMICalculatorHandler();
    }

    @Test
    void testValidBMICalculation() {
        // Test case for normal weight
        BMIRequest request = new BMIRequest(170, 70); // 170cm, 70kg
        BMIResponse response = handler.calculateBMI(request);

        assertNotNull(response);
        assertNull(response.getError());
        assertNotNull(response.getBmi());
        assertEquals("24.22", response.getBmi());
        assertEquals("Normal weight", response.getCategory());
    }

    @Test
    void testUnderweightBMICalculation() {
        BMIRequest request = new BMIRequest(170, 50); // 170cm, 50kg
        BMIResponse response = handler.handleRequest(request, context);

        assertNotNull(response);
        assertNull(response.getError());
        assertEquals("17.30", response.getBmi());
        assertEquals("Underweight", response.getCategory());
    }

    @Test
    void testOverweightBMICalculation() {
        BMIRequest request = new BMIRequest(170, 80); // 170cm, 80kg
        BMIResponse response = handler.handleRequest(request, context);

        assertNotNull(response);
        assertNull(response.getError());
        assertEquals("27.68", response.getBmi());
        assertEquals("Overweight", response.getCategory());
    }

    @Test
    void testObeseBMICalculation() {
        BMIRequest request = new BMIRequest(170, 100); // 170cm, 100kg
        BMIResponse response = handler.handleRequest(request, context);

        assertNotNull(response);
        assertNull(response.getError());
        assertEquals("34.60", response.getBmi());
        assertEquals("Obese", response.getCategory());
    }

    @Test
    void testInvalidHeight() {
        BMIRequest request = new BMIRequest(0, 70);
        BMIResponse response = handler.handleRequest(request, context);

        assertNotNull(response);
        assertNotNull(response.getError());
        assertTrue(response.getError().contains("Height must be greater than 0"));
    }

    @Test
    void testInvalidWeight() {
        BMIRequest request = new BMIRequest(170, -1);
        BMIResponse response = handler.handleRequest(request, context);

        assertNotNull(response);
        assertNotNull(response.getError());
        assertTrue(response.getError().contains("Weight must be greater than 0"));
    }

    @Test
    void testNullRequest() {
        BMIResponse response = handler.handleRequest(null, context);

        assertNotNull(response);
        assertNotNull(response.getError());
        assertTrue(response.getError().contains("Request cannot be null"));
    }
}