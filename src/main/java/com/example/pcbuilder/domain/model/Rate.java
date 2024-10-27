package com.example.pcbuilder.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rates")
public class Rate {

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

    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
