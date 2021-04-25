package ru.bks.model;

import lombok.Getter;
import org.springframework.web.client.RestTemplate;

@Getter
public class Stock {
    private final String symbol;
    private final Long volume;
    private final String sector;
    private final Double latestPrice;
    private final Double assetValue;

    public Stock(String symbol, Long volume) {
        this.symbol = symbol;
        this.volume = volume;
        RestTemplate restTemplate = new RestTemplate();
        Sector sector = restTemplate.getForObject(String.format("https://cloud.iexapis.com/stable/stock/%s/company?token=pk_04808889791e4541a811d8ac9403fe02", symbol), Sector.class);
        this.sector = sector.getSector();
        this.latestPrice = restTemplate.getForObject(String.format("https://cloud.iexapis.com/stable/stock/%s/quote/latestPrice?token=pk_04808889791e4541a811d8ac9403fe02", symbol), Double.class);
        this.assetValue  = getLatestPrice() * getVolume();
    }
}
