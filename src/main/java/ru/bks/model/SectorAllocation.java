
package ru.bks.model;


public class SectorAllocation {
    public final String sector;
    public final Double assetValue;
    public final Double proportion;

    public SectorAllocation(String sector, Double assetValue, Double proportion) {
        this.sector = sector;
        this.assetValue = assetValue;
        this.proportion = proportion;
    }
}