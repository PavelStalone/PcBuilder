package com.example.pcbuilder.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    private int cost;
    private User user;
    private Build build;

    protected Order() {
    }

    @Column(name = "cost", nullable = false)
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @EmbeddedId
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @EmbeddedId
    @ManyToOne(optional = false)
    @JoinColumn(name = "build_id", referencedColumnName = "id", nullable = false)
    public Build getBuild() {
        return build;
    }

    public void setBuild(Build build) {
        this.build = build;
    }
}
