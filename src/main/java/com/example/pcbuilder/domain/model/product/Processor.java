package com.example.pcbuilder.domain.model.product;

import com.example.pcbuilder.domain.model.BaseProduct;
import com.example.pcbuilder.domain.model.Build;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "processors")
public class Processor extends BaseProduct {

    private int year;
    private int cores;
    private int threads;
    private int maxFreq;
    private int baseFreq;
    private String model;
    private int maxMemory;
    private String socket;
    private Set<Build> build;
    private String[] memoryType;
    private boolean hasGraphicsCore;

    protected Processor() {
    }

    @Column(name = "year", nullable = false)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Column(name = "cores", nullable = false)
    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    @Column(name = "threads", nullable = false)
    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    @Column(name = "max_freq", nullable = false)
    public int getMaxFreq() {
        return maxFreq;
    }

    public void setMaxFreq(int maxFreq) {
        this.maxFreq = maxFreq;
    }

    @Column(name = "base_freq", nullable = false)
    public int getBaseFreq() {
        return baseFreq;
    }

    public void setBaseFreq(int baseFreq) {
        this.baseFreq = baseFreq;
    }

    @Column(name = "model", nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "max_memory", nullable = false)
    public int getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(int maxMemory) {
        this.maxMemory = maxMemory;
    }

    @Column(name = "socket", nullable = false)
    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    @OneToMany(
            mappedBy = "cpu",
            targetEntity = Build.class,
            fetch = FetchType.LAZY
    )
    public Set<Build> getBuild() {
        return build;
    }

    public void setBuild(Set<Build> build) {
        this.build = build;
    }

    @Column(name = "memory_type", nullable = false)
    public String[] getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String[] memoryType) {
        this.memoryType = memoryType;
    }

    @Column(name = "has_graphics_core", nullable = false)
    public boolean isHasGraphicsCore() {
        return hasGraphicsCore;
    }

    public void setHasGraphicsCore(boolean hasGraphicsCore) {
        this.hasGraphicsCore = hasGraphicsCore;
    }
}
