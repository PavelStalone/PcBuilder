package com.example.pcbuilder.data.model.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SsdDto extends ProductDto {

    private int tbw;
    private int maxPower;
    private String model;
    private int maxReadSpeed;
    private int maxWriteSpeed;
    private int memoryCapacity;
    private String interfaceType;

    public SsdDto() {
    }

    @Min(0)
    @NotNull
    public int getTbw() {
        return tbw;
    }

    public void setTbw(int tbw) {
        this.tbw = tbw;
    }

    @NotNull
    @Min(0)
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

    @NotNull
    public int getMaxReadSpeed() {
        return maxReadSpeed;
    }

    public void setMaxReadSpeed(int maxReadSpeed) {
        this.maxReadSpeed = maxReadSpeed;
    }

    @NotNull
    @Min(0)
    public int getMaxWriteSpeed() {
        return maxWriteSpeed;
    }

    public void setMaxWriteSpeed(int maxWriteSpeed) {
        this.maxWriteSpeed = maxWriteSpeed;
    }

    @NotNull
    @Min(0)
    public int getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(int memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
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
