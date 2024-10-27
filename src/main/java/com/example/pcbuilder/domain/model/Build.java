package com.example.pcbuilder.domain.model;

import com.example.pcbuilder.domain.model.product.*;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "builds")
public class Build extends BaseProduct {

    private RAM ram;
    private SSD ssd;
    private HDD hdd;
    private Case pcCase;
    private User owner;
    private int ramCounts;
    private Processor cpu;
    private Set<Tag> tags;
    private int orderCounts;
    private GraphicsCard gpu;
    private Set<Order> orders;
    private float averageRate;
    private String description;
    private PowerUnit powerUnit;
    private Motherboard motherboard;

    protected Build() {
    }

    @ManyToOne
    @JoinColumn(name = "ram_id", referencedColumnName = "id")
    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    @ManyToOne
    @JoinColumn(name = "ssd_id", referencedColumnName = "id")
    public SSD getSsd() {
        return ssd;
    }

    public void setSsd(SSD ssd) {
        this.ssd = ssd;
    }

    @ManyToOne
    @JoinColumn(name = "hdd_id", referencedColumnName = "id")
    public HDD getHdd() {
        return hdd;
    }

    public void setHdd(HDD hdd) {
        this.hdd = hdd;
    }

    @ManyToOne
    @JoinColumn(name = "case_id", referencedColumnName = "id")
    public Case getPcCase() {
        return pcCase;
    }

    public void setPcCase(Case pcCase) {
        this.pcCase = pcCase;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Column(name = "ram_counts", nullable = false)
    public int getRamCounts() {
        return ramCounts;
    }

    public void setRamCounts(int ramCounts) {
        this.ramCounts = ramCounts;
    }

    @ManyToOne
    @JoinColumn(name = "cpu_id", referencedColumnName = "id")
    public Processor getCpu() {
        return cpu;
    }

    public void setCpu(Processor cpu) {
        this.cpu = cpu;
    }

    @ManyToMany(
            mappedBy = "builds",
            targetEntity = Tag.class,
            fetch = FetchType.EAGER
    )
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Column(name = "order_counts", nullable = false)
    public int getOrderCounts() {
        return orderCounts;
    }

    public void setOrderCounts(int orderCounts) {
        this.orderCounts = orderCounts;
    }

    @ManyToOne
    @JoinColumn(name = "gpu_id", referencedColumnName = "id")
    public GraphicsCard getGpu() {
        return gpu;
    }

    public void setGpu(GraphicsCard gpu) {
        this.gpu = gpu;
    }

    @OneToMany(mappedBy = "build", targetEntity = Order.class, fetch = FetchType.LAZY)
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Column(name = "average_rate", nullable = false)
    public float getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(float averageRate) {
        this.averageRate = averageRate;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "power_unit_id", referencedColumnName = "id")
    public PowerUnit getPowerUnit() {
        return powerUnit;
    }

    public void setPowerUnit(PowerUnit powerUnit) {
        this.powerUnit = powerUnit;
    }

    @ManyToOne
    @JoinColumn(name = "motherboard_id", referencedColumnName = "id")
    public Motherboard getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }
}
