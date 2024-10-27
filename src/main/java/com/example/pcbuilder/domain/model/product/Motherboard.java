package com.example.pcbuilder.domain.model.product;

import com.example.pcbuilder.domain.model.BaseProduct;
import com.example.pcbuilder.domain.model.Build;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "motherboards")
public class Motherboard extends BaseProduct {

    private String model;
    private Set<Build> build;
    private int maxMemoryFreq;
    private String formFactor;
    private String memoryType;
    private String cpuConnectors;
    private int maxMemoryCapacity;
    private int memorySlotsCounts;
    private String baseConnectors;
    private int graphicSlotsCounts;
    private String processorSocket;
    private String memoryFormFactor;
    private String graphicsSlotType;

    protected Motherboard() {
    }

    @Column(name = "model", nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @OneToMany(
            mappedBy = "motherboard",
            targetEntity = Build.class,
            fetch = FetchType.LAZY
    )
    public Set<Build> getBuild() {
        return build;
    }

    public void setBuild(Set<Build> build) {
        this.build = build;
    }

    @Column(name = "max_memory_freq", nullable = false)
    public int getMaxMemoryFreq() {
        return maxMemoryFreq;
    }

    public void setMaxMemoryFreq(int maxMemoryFreq) {
        this.maxMemoryFreq = maxMemoryFreq;
    }

    @Column(name = "form_factor", nullable = false)
    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    @Column(name = "memory_type", nullable = false)
    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    @Column(name = "cpu_connectors", nullable = false)
    public String getCpuConnectors() {
        return cpuConnectors;
    }

    public void setCpuConnectors(String cpuConnectors) {
        this.cpuConnectors = cpuConnectors;
    }

    @Column(name = "max_memory_capacity", nullable = false)
    public int getMaxMemoryCapacity() {
        return maxMemoryCapacity;
    }

    public void setMaxMemoryCapacity(int maxMemoryCapacity) {
        this.maxMemoryCapacity = maxMemoryCapacity;
    }

    @Column(name = "memory_slots_counts", nullable = false)
    public int getMemorySlotsCounts() {
        return memorySlotsCounts;
    }

    public void setMemorySlotsCounts(int memorySlotsCounts) {
        this.memorySlotsCounts = memorySlotsCounts;
    }

    @Column(name = "base_connectors", nullable = false)
    public String getBaseConnectors() {
        return baseConnectors;
    }

    public void setBaseConnectors(String baseConnectors) {
        this.baseConnectors = baseConnectors;
    }

    @Column(name = "graphic_slots_counts", nullable = false)
    public int getGraphicSlotsCounts() {
        return graphicSlotsCounts;
    }

    public void setGraphicSlotsCounts(int graphicSlotsCounts) {
        this.graphicSlotsCounts = graphicSlotsCounts;
    }

    @Column(name = "processor_socket", nullable = false)
    public String getProcessorSocket() {
        return processorSocket;
    }

    public void setProcessorSocket(String processorSocket) {
        this.processorSocket = processorSocket;
    }

    @Column(name = "memory_form_factor", nullable = false)
    public String getMemoryFormFactor() {
        return memoryFormFactor;
    }

    public void setMemoryFormFactor(String memoryFormFactor) {
        this.memoryFormFactor = memoryFormFactor;
    }

    @Column(name = "graphics_slot_type", nullable = false)
    public String getGraphicsSlotType() {
        return graphicsSlotType;
    }

    public void setGraphicsSlotType(String graphicsSlotType) {
        this.graphicsSlotType = graphicsSlotType;
    }
}
