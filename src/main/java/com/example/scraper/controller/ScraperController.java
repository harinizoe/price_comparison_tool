package com.example.scraper.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.scraper.model.ProductResponse;
import com.example.scraper.service.AmazonScraperService;

@RestController
@RequestMapping("/api/scrape")
@CrossOrigin(origins = "http://localhost:3000")
public class ScraperController {
    private final AmazonScraperService amazonScraperService;

    public ScraperController(AmazonScraperService amazonScraperService) {
        this.amazonScraperService = amazonScraperService;
    }

    @GetMapping("/amazon")
    public ProductResponse getAmazonProduct(@RequestParam String product) {
        return amazonScraperService.scrapeAmazonPrice(product);
    }
}
