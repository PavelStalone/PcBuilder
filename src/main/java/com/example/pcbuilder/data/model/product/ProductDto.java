package com.example.pcbuilder.data.model.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public abstract class ProductDto {

    private int cost;

    @Min(0)
    @NotNull
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
