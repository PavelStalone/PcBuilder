package com.example.pcbuilder.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rates")
public class Rate extends BaseEntity {

    private int rate;
    private User user;
    private Build build;
    private String comment;

    protected Rate() {
    }

    @Column(name = "rate", nullable = false)
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "build_id", referencedColumnName = "id", nullable = false)
    public Build getBuild() {
        return build;
    }

    public void setBuild(Build build) {
        this.build = build;
    }

    @Column(name = "comment", length = 511)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "rate=" + rate +
                ", user=" + user +
                ", build=" + build +
                ", comment='" + comment + '\'' +
                '}';
    }
}
