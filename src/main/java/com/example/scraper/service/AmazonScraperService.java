package com.example.scraper.service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.example.scraper.model.ProductResponse;

@Service
public class AmazonScraperService {

    public ProductResponse scrapeAmazonPrice(String productName) {
        try {
            String url = "https://www.amazon.in/s?k=" + productName.replace(" ", "+");
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(10000)
                    .get();

            Element item = doc.select("div.s-main-slot div[data-component-type='s-search-result']").first();

            if (item == null) return new ProductResponse("N/A", "N/A", "N/A");

            String title = item.select("h2 span").text();
            String price = item.select("span.a-price-whole").text();
            String link = "https://www.amazon.in" + item.select("h2 a").attr("href");

            return new ProductResponse(title, price, link);

        } catch (Exception e) {
            return new ProductResponse("Error", "0", "N/A");
        }
    }
}
