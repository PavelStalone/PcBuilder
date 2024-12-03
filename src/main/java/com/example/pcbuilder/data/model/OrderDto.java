package com.example.pcbuilder.data.model;

import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class OrderDto {

    private int cost;
    private String userName;
    private BuildDto buildDto;

    public OrderDto() {
    }

    @Min(0)
    @NotNull
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @NotNull
    @NotEmpty
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NotNull
    public BuildDto getBuildDto() {
        return buildDto;
    }

    public void setBuildDto(BuildDto buildDto) {
        this.buildDto = buildDto;
    }
}
