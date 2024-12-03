package com.example.pcbuilder.data.model.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class HddDto extends ProductDto {

    private int maxPower;
    private String model;
    private int cacheMemory;
    private int rotationSpeed;
    private int memoryCapacity;
    private int maxSpeedTransfer;
    private String interfaceType;

    public HddDto() {
    }

    @Min(0)
    @NotNull
    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    @NotNull
    @NotEmpty
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Min(0)
    @NotNull
    public int getCacheMemory() {
        return cacheMemory;
    }

    public void setCacheMemory(int cacheMemory) {
        this.cacheMemory = cacheMemory;
    }

    @Min(0)
    @NotNull
    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    @Min(0)
    @NotNull
    public int getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(int memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    @Min(0)
    @NotNull
    public int getMaxSpeedTransfer() {
        return maxSpeedTransfer;
    }

    public void setMaxSpeedTransfer(int maxSpeedTransfer) {
        this.maxSpeedTransfer = maxSpeedTransfer;
    }

    @NotNull
    @NotEmpty
    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }
}
