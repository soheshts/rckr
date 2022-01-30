package io.github.soheshts.model;

import java.util.Arrays;
import java.util.List;

public class Country {
    String name;
    Long population;
    List<Currency> currencies;
    double[] latlng=new double[2];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public double[] getLatlng() {
        return latlng;
    }

    public void setLatlng(double[] latlng) {
        this.latlng = latlng;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", population=" + population +
                ", currencies=" + currencies +
                ", latlng=" + Arrays.toString(latlng) +
                '}';
    }
}
