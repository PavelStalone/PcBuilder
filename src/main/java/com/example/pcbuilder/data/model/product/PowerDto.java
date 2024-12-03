package com.example.pcbuilder.data.model.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PowerDto extends ProductDto {

    private int power;
    private String model;
    private String formFactor;

    public PowerDto() {
    }

    @Min(0)
    @NotNull
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @NotNull
    @NotEmpty
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @NotNull
    @NotEmpty
    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }
}
