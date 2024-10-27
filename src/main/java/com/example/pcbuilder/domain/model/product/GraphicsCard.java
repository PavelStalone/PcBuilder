package com.example.pcbuilder.domain.model.product;

import com.example.pcbuilder.domain.model.BaseProduct;
import com.example.pcbuilder.domain.model.Build;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "graphics_cards")
public class GraphicsCard extends BaseProduct {

    private int freq;
    private int minPower;
    private String model;
    private String slotType;
    private Set<Build> build;
    private String memoryType;
    private int memoryCapacity;

    protected GraphicsCard() {
    }

    @Column(name = "freq", nullable = false)
    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    @Column(name = "min_power", nullable = false)
    public int getMinPower() {
        return minPower;
    }

    public void setMinPower(int minPower) {
        this.minPower = minPower;
    }

    @Column(name = "model", nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "slot_type", nullable = false)
    public String getSlotType() {
        return slotType;
    }

    public void setSlotType(String slotType) {
        this.slotType = slotType;
    }

    @OneToMany(
            mappedBy = "gpu",
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
    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    @Column(name = "memory_capacity", nullable = false)
    public int getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(int memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }
}
