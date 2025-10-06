package com.example.scraper.model;
public class ProductResponse {
    private String title;
    private String price;
    private String url;

    public ProductResponse() {}

    public ProductResponse(String title, String price, String url) {
        this.title = title;
        this.price = price;
        this.url = url;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
