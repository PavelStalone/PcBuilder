package com.example.pcbuilder.domain.model.product;

import com.example.pcbuilder.domain.model.BaseProduct;
import com.example.pcbuilder.domain.model.Build;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "ram")
public class RAM extends BaseProduct {

    private int freq;
    private String model;
    private Set<Build> build;
    private String memoryType;
    private String formFactor;
    private int memoryCapacity;

    protected RAM() {
    }

    @Column(name = "freq", nullable = false)
    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    @Column(name = "model", nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @OneToMany(
            mappedBy = "ram",
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

    @Column(name = "form_factor", nullable = false)
    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    @Column(name = "memory_capacity", nullable = false)
    public int getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(int memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }
}
