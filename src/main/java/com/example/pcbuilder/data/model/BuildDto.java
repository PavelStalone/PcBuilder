package com.example.pcbuilder.data.model;

import com.example.pcbuilder.data.model.product.*;
import edu.rutmiit.example.pcbuildercontracts.viewmodel.build.TagDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class BuildDto {

    private RamDto ram;
    private SsdDto ssd;
    private HddDto hdd;
    private CaseDto pcCase;
    private int ramCounts;
    private CpuDto cpu;
    private Set<TagDto> tags;
    private int orderCounts;
    private String ownerName;
    private GraphicsCardDto gpu;
    private float averageRate;
    private String description;
    private PowerDto powerUnit;
    private MotherboardDto motherboard;

    public BuildDto() {
    }

    public RamDto getRam() {
        return ram;
    }

    public void setRam(RamDto ram) {
        this.ram = ram;
    }

    public SsdDto getSsd() {
        return ssd;
    }

    public void setSsd(SsdDto ssd) {
        this.ssd = ssd;
    }

    public HddDto getHdd() {
        return hdd;
    }

    public void setHdd(HddDto hdd) {
        this.hdd = hdd;
    }

    public CaseDto getPcCase() {
        return pcCase;
    }

    public void setPcCase(CaseDto pcCase) {
        this.pcCase = pcCase;
    }

    @Min(0)
    @NotNull
    public int getRamCounts() {
        return ramCounts;
    }

    public void setRamCounts(int ramCounts) {
        this.ramCounts = ramCounts;
    }

    public CpuDto getCpu() {
        return cpu;
    }

    public void setCpu(CpuDto cpu) {
        this.cpu = cpu;
    }

    @NotNull
    @NotEmpty
    public Set<TagDto> getTags() {
        return tags;
    }

    public void setTags(Set<TagDto> tags) {
        this.tags = tags;
    }

    @Min(0)
    @NotNull
    public int getOrderCounts() {
        return orderCounts;
    }

    public void setOrderCounts(int orderCounts) {
        this.orderCounts = orderCounts;
    }

    @NotNull
    @NotEmpty
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public GraphicsCardDto getGpu() {
        return gpu;
    }

    public void setGpu(GraphicsCardDto gpu) {
        this.gpu = gpu;
    }

    public float getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(float averageRate) {
        this.averageRate = averageRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PowerDto getPowerUnit() {
        return powerUnit;
    }

    public void setPowerUnit(PowerDto powerUnit) {
        this.powerUnit = powerUnit;
    }

    public MotherboardDto getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(MotherboardDto motherboard) {
        this.motherboard = motherboard;
    }
}
