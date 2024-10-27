package com.example.pcbuilder.domain.model.product;

import com.example.pcbuilder.domain.model.BaseProduct;
import com.example.pcbuilder.domain.model.Build;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "power_units")
public class PowerUnit extends BaseProduct {

    private int power;
    private String model;
    private Set<Build> build;
    private String formFactor;
    private String cpuConnectors;
    private String baseConnectors;

    protected PowerUnit() {
    }

    @Column(name = "power", nullable = false)
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Column(name = "model", nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @OneToMany(
            mappedBy = "powerUnit",
            targetEntity = Build.class,
            fetch = FetchType.LAZY
    )
    public Set<Build> getBuild() {
        return build;
    }

    public void setBuild(Set<Build> build) {
        this.build = build;
    }

    @Column(name = "form_factor", nullable = false)
    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    @Column(name = "cpu_connectors", nullable = false)
    public String getCpuConnectors() {
        return cpuConnectors;
    }

    public void setCpuConnectors(String cpuConnectors) {
        this.cpuConnectors = cpuConnectors;
    }

    @Column(name = "base_connectors", nullable = false)
    public String getBaseConnectors() {
        return baseConnectors;
    }

    public void setBaseConnectors(String baseConnectors) {
        this.baseConnectors = baseConnectors;
    }
}
