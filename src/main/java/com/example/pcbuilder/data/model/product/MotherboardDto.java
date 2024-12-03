package com.example.pcbuilder.data.model.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class MotherboardDto extends ProductDto {

    private String model;
    private int maxMemoryFreq;
    private String formFactor;
    private String memoryType;
    private int maxMemoryCapacity;
    private int memorySlotsCounts;
    private int graphicSlotsCounts;
    private String processorSocket;
    private String memoryFormFactor;
    private String graphicsSlotType;

    public MotherboardDto() {
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
    public int getMaxMemoryFreq() {
        return maxMemoryFreq;
    }

    public void setMaxMemoryFreq(int maxMemoryFreq) {
        this.maxMemoryFreq = maxMemoryFreq;
    }

    @NotNull
    @NotEmpty
    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    @NotNull
    @NotEmpty
    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    @Min(0)
    @NotNull
    public int getMaxMemoryCapacity() {
        return maxMemoryCapacity;
    }

    public void setMaxMemoryCapacity(int maxMemoryCapacity) {
        this.maxMemoryCapacity = maxMemoryCapacity;
    }

    @Min(0)
    @NotNull
    public int getMemorySlotsCounts() {
        return memorySlotsCounts;
    }

    public void setMemorySlotsCounts(int memorySlotsCounts) {
        this.memorySlotsCounts = memorySlotsCounts;
    }

    @Min(0)
    @NotNull
    public int getGraphicSlotsCounts() {
        return graphicSlotsCounts;
    }

    public void setGraphicSlotsCounts(int graphicSlotsCounts) {
        this.graphicSlotsCounts = graphicSlotsCounts;
    }

    @NotNull
    @NotEmpty
    public String getProcessorSocket() {
        return processorSocket;
    }

    public void setProcessorSocket(String processorSocket) {
        this.processorSocket = processorSocket;
    }

    @NotNull
    @NotEmpty
    public String getMemoryFormFactor() {
        return memoryFormFactor;
    }

    public void setMemoryFormFactor(String memoryFormFactor) {
        this.memoryFormFactor = memoryFormFactor;
    }

    @NotNull
    @NotEmpty
    public String getGraphicsSlotType() {
        return graphicsSlotType;
    }

    public void setGraphicsSlotType(String graphicsSlotType) {
        this.graphicsSlotType = graphicsSlotType;
    }
}
