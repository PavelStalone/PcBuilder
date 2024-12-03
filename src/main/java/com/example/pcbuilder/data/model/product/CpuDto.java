package com.example.pcbuilder.data.model.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CpuDto extends ProductDto {

    private int year;
    private int cores;
    private int threads;
    private int maxFreq;
    private int baseFreq;
    private String model;
    private int maxMemory;
    private String socket;
    private String[] memoryType;
    private boolean hasGraphicsCore;

    public CpuDto() {
    }

    @NotNull
    @Min(value = 1800)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @NotNull
    @Min(value = 1)
    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    @NotNull
    @Min(value = 1)
    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    @NotNull
    public int getMaxFreq() {
        return maxFreq;
    }

    public void setMaxFreq(int maxFreq) {
        this.maxFreq = maxFreq;
    }

    @NotNull
    public int getBaseFreq() {
        return baseFreq;
    }

    public void setBaseFreq(int baseFreq) {
        this.baseFreq = baseFreq;
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
    public int getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(int maxMemory) {
        this.maxMemory = maxMemory;
    }

    @NotNull
    @NotEmpty
    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    @NotNull
    @NotEmpty
    public String[] getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String[] memoryType) {
        this.memoryType = memoryType;
    }

    @NotNull
    public boolean isHasGraphicsCore() {
        return hasGraphicsCore;
    }

    public void setHasGraphicsCore(boolean hasGraphicsCore) {
        this.hasGraphicsCore = hasGraphicsCore;
    }
}
