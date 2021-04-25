package ru.bks.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.bks.model.Allocations;
import ru.bks.model.Portfolio;
import ru.bks.service.PortfolioService;

@RestController()
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Allocations calculatePortfolio(@RequestBody Portfolio portfolio) {
        return service.CalculatePortfolio(portfolio);
    }
}