package com.example.pcbuilder.domain.entity.product;

import com.example.pcbuilder.domain.entity.BaseProduct;
import com.example.pcbuilder.domain.entity.Build;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "ssd")
public class SSD extends BaseProduct {

    private int tbw;
    private int maxPower;
    private String model;
    private Set<Build> build;
    private int maxReadSpeed;
    private int maxWriteSpeed;
    private int memoryCapacity;
    private String interfaceType;

    protected SSD() {
    }

    @Column(name = "tbw", nullable = false)
    public int getTbw() {
        return tbw;
    }

    public void setTbw(int tbw) {
        this.tbw = tbw;
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

    @OneToMany(
            mappedBy = "ssd",
            targetEntity = Build.class,
            fetch = FetchType.LAZY
    )
    public Set<Build> getBuild() {
        return build;
    }

    public void setBuild(Set<Build> build) {
        this.build = build;
    }

    @Column(name = "max_read_speed", nullable = false)
    public int getMaxReadSpeed() {
        return maxReadSpeed;
    }

    public void setMaxReadSpeed(int maxReadSpeed) {
        this.maxReadSpeed = maxReadSpeed;
    }

    @Column(name = "max_write_speed", nullable = false)
    public int getMaxWriteSpeed() {
        return maxWriteSpeed;
    }

    public void setMaxWriteSpeed(int maxWriteSpeed) {
        this.maxWriteSpeed = maxWriteSpeed;
    }

    @Column(name = "memory_capacity", nullable = false)
    public int getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(int memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    @Column(name = "interface_type", nullable = false)
    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }
}
