package com.bmi.calculator;

public class BMIRequest {
    private double height; // in centimeters
    private double weight; // in kilograms

    public BMIRequest() {
    }

    public BMIRequest(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}