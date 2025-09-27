package com.bmi.calculator;

public class BMIResponse {
    private String bmi;
    private String category;
    private String error;

    private BMIResponse(Builder builder) {
        this.bmi = builder.bmi;
        this.category = builder.category;
        this.error = builder.error;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getBmi() {
        return bmi;
    }

    public String getCategory() {
        return category;
    }

    public String getError() {
        return error;
    }

    public static class Builder {
        private String bmi;
        private String category;
        private String error;

        public Builder bmi(String bmi) {
            this.bmi = bmi;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public BMIResponse build() {
            return new BMIResponse(this);
        }
    }
}