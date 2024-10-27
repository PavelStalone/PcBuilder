package com.example.pcbuilder.domain.model.product;

import com.example.pcbuilder.domain.model.BaseProduct;
import com.example.pcbuilder.domain.model.Build;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "cases")
public class Case extends BaseProduct {

    private String model;
    private Set<Build> build;
    private String powerFactor;
    private String motherboardFactor;

    protected Case() {
    }

    @Column(name = "model", nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @OneToMany(
            mappedBy = "pcCase",
            targetEntity = Build.class,
            fetch = FetchType.LAZY
    )
    public Set<Build> getBuild() {
        return build;
    }

    public void setBuild(Set<Build> build) {
        this.build = build;
    }

    @Column(name = "power_factor", nullable = false)
    public String getPowerFactor() {
        return powerFactor;
    }

    public void setPowerFactor(String powerFactor) {
        this.powerFactor = powerFactor;
    }

    @Column(name = "motherboard_factor", nullable = false)
    public String getMotherboardFactor() {
        return motherboardFactor;
    }

    public void setMotherboardFactor(String motherboardFactor) {
        this.motherboardFactor = motherboardFactor;
    }
}
