package com.example.pcbuilder.domain.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag extends BaseEntity {

    private String name;
    private Set<Build> builds;
    private String description;

    protected Tag() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(
            mappedBy = "tags",
            fetch = FetchType.EAGER
    )
    public Set<Build> getBuilds() {
        return builds;
    }

    public void setBuilds(Set<Build> builds) {
        this.builds = builds;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
