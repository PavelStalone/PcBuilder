package com.example.pcbuilder.data.model.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CaseDto extends ProductDto {

    private String model;
    private String powerFactor;
    private String motherboardFactor;

    public CaseDto() {
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
    public String getPowerFactor() {
        return powerFactor;
    }

    public void setPowerFactor(String powerFactor) {
        this.powerFactor = powerFactor;
    }

    @NotNull
    @NotEmpty
    public String getMotherboardFactor() {
        return motherboardFactor;
    }

    public void setMotherboardFactor(String motherboardFactor) {
        this.motherboardFactor = motherboardFactor;
    }
}
