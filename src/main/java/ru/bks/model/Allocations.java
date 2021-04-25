package ru.bks.model;

import java.util.ArrayList;
import java.util.List;

public class Allocations {
    public Double value;
    public List<SectorAllocation> allocations = new ArrayList<>();

    public Allocations(Double value) {
        this.value = value;
    }
}