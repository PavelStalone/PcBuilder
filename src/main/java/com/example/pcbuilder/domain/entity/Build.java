package com.example.pcbuilder.domain.entity;

import com.example.pcbuilder.domain.entity.product.*;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Entity
@Table(name = "builds")
public class Build extends BaseEntity {

    private RAM ram;
    private SSD ssd;
    private HDD hdd;
    private int cost;
    private Case pcCase;
    private User owner;
    private int ramCounts;
    private int gpuCounts;
    private Processor cpu;
    private Set<Tag> tags;
    private int orderCounts;
    private Set<Rate> rates;
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

    @Formula("(SELECT " +
            "COALESCE(r.cost * b.ram_counts, 0) + " +
            "COALESCE(s.cost, 0) + " +
            "COALESCE(h.cost, 0) + " +
            "COALESCE(c.cost, 0) + " +
            "COALESCE(p.cost, 0) + " +
            "COALESCE(g.cost * b.gpu_counts, 0) + " +
            "COALESCE(pu.cost, 0) + " +
            "COALESCE(m.cost, 0) " +
            "FROM builds b " +
            "LEFT JOIN ram r ON b.ram_id = r.id " +
            "LEFT JOIN ssd s ON b.ssd_id = s.id " +
            "LEFT JOIN hdd h ON b.hdd_id = h.id " +
            "LEFT JOIN cases c ON b.case_id = c.id " +
            "LEFT JOIN processors p ON b.cpu_id = p.id " +
            "LEFT JOIN graphics_cards g ON b.gpu_id = g.id " +
            "LEFT JOIN power_units pu ON b.power_unit_id = pu.id " +
            "LEFT JOIN motherboards m ON b.motherboard_id = m.id " +
            "WHERE b.id = id)")
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @ManyToOne
    @JoinColumn(name = "case_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
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

    @Column(name = "gpu_counts", nullable = false)
    public int getGpuCounts() {
        return gpuCounts;
    }

    public void setGpuCounts(int gpuCounts) {
        this.gpuCounts = gpuCounts;
    }

    @ManyToOne
    @JoinColumn(name = "cpu_id", referencedColumnName = "id")
    public Processor getCpu() {
        return cpu;
    }

    public void setCpu(Processor cpu) {
        this.cpu = cpu;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tags_builds",
            joinColumns = @JoinColumn(name = "build_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
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

    @OneToMany(mappedBy = "build", targetEntity = Rate.class, fetch = FetchType.LAZY)
    public Set<Rate> getRates() {
        return rates;
    }

    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }

    @ManyToOne
    @JoinColumn(name = "gpu_id", referencedColumnName = "id")
    public GraphicsCard getGpu() {
        return gpu;
    }

    public void setGpu(GraphicsCard gpu) {
        this.gpu = gpu;
    }

    @OneToMany(mappedBy = "build", targetEntity = Order.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Formula("(SELECT " +
            "COALESCE(AVG(r.rate), 0) " +
            "FROM builds b " +
            "LEFT JOIN rates r ON b.id = r.build_id " +
            "WHERE b.id = id " +
            "GROUP BY r.build_id)")
    public float getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(float averageRate) {
        this.averageRate = averageRate;
    }

    @Column(name = "description", length = 511)
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
