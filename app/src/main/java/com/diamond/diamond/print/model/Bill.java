package com.diamond.diamond.print.model;

import java.util.List;

public class Bill {
    String ID;
    List<Product> products;
    long time;
    String responsible;
    String total;

    public Bill() {
    }

    public Bill(List<Product> products, long time, String responsible, String total) {
        this.products = products;
        this.time = time;
        this.responsible = responsible;
        this.total = total;
    }

    public Bill(String ID, List<Product> products, long time, String responsible, String total) {
        this.ID = ID;
        this.products = products;
        this.time = time;
        this.responsible = responsible;
        this.total = total;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
