package com.example.pcbuilder.domain.entity.product;

import com.example.pcbuilder.domain.entity.BaseProduct;
import com.example.pcbuilder.domain.entity.Build;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "hdd")
public class HDD extends BaseProduct {

    private int maxPower;
    private String model;
    private int cacheMemory;
    private Set<Build> build;
    private int rotationSpeed;
    private int memoryCapacity;
    private int maxSpeedTransfer;
    private String interfaceType;

    protected HDD() {
    }

    @Column(name = "max_power", nullable = false)
    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    @Column(name = "model", nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "cache_memory", nullable = false)
    public int getCacheMemory() {
        return cacheMemory;
    }

    public void setCacheMemory(int cacheMemory) {
        this.cacheMemory = cacheMemory;
    }

    @OneToMany(
            mappedBy = "hdd",
            targetEntity = Build.class,
            fetch = FetchType.LAZY
    )
    public Set<Build> getBuild() {
        return build;
    }

    public void setBuild(Set<Build> build) {
        this.build = build;
    }

    @Column(name = "rotation_speed", nullable = false)
    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    @Column(name = "memory_capacity", nullable = false)
    public int getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(int memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    @Column(name = "max_speed_transfer", nullable = false)
    public int getMaxSpeedTransfer() {
        return maxSpeedTransfer;
    }

    public void setMaxSpeedTransfer(int maxSpeedTransfer) {
        this.maxSpeedTransfer = maxSpeedTransfer;
    }

    @Column(name = "interface_type", nullable = false)
    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }
}
