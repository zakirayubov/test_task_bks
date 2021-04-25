package ru.bks.service;

import org.springframework.stereotype.Service;
import ru.bks.model.Allocations;
import ru.bks.model.Portfolio;
import ru.bks.model.Stock;
import ru.bks.model.SectorAllocation;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PortfolioService {

    public Allocations CalculatePortfolio (Portfolio portfolio) {
        Double totalCost = calculateTotalCost(portfolio);
        Allocations portfolioAllocations = new Allocations(totalCost);
        portfolio.stocks
                .stream()
                .map(Stock::getSector)
                .distinct()
                .forEach(s -> portfolioAllocations.allocations.add(CalculateSectorAllocations(portfolio, s, totalCost)));

        return portfolioAllocations;
    }

    private SectorAllocation CalculateSectorAllocations(Portfolio portfolio, String sector, Double total){
        Double assetValue = portfolio.stocks
                .stream()
                .filter(s -> sector.equals(s.getSector()))
                .mapToDouble(Stock::getAssetValue)
                .sum();

        Double proportion = new BigDecimal(assetValue / total).setScale(3, RoundingMode.HALF_UP).doubleValue();
        return new SectorAllocation(sector,assetValue,proportion);
    }

    private Double calculateTotalCost(Portfolio portfolio) {
        return portfolio.stocks
                .stream()
                .mapToDouble(Stock::getAssetValue)
                .sum();
    }
}
