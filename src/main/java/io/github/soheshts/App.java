package io.github.soheshts;

import io.github.soheshts.model.Country;
import io.github.soheshts.util.Constants;
import io.github.soheshts.util.Util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    private static final Long populationLimit = 65110000L;

    public static void main(String[] args) {
        List<Country> countryList;
        List<Country> filteredList;
//        BigDecimal distance = new BigDecimal(0);
        double distance = 0.0;
        try {
            System.out.println("Getting CountryList");
            countryList = Util.readJsonFromUrl(Constants.jsonURL);
            System.out.println("Got CountryList");
            filteredList = countryList.stream().filter(country -> country.getCurrencies().size() > 0
                    && country.getPopulation() >= populationLimit).collect(Collectors.toList());
            Collections.sort(filteredList, (c1, c2) -> c1.getPopulation().compareTo(c2.getPopulation()));
            Collections.reverse(filteredList);
            filteredList.subList(20, filteredList.size()).clear();
            System.out.println(filteredList.toString());
            for (int i = 0; i < filteredList.size() - 1; i++) {
                Country c1 = filteredList.get(i);
                Country c2 = filteredList.get(i + 1);
                System.out.print(c1.getName() +"( "+c1.getLatlng()[0]+" , "+ c1.getLatlng()[1]+" )"
                        + " and " + c2.getName() +"( "+c2.getLatlng()[0]+" , "+ c2.getLatlng()[1]+" )"  + " : ");
                distance = distance + Util.distance(c1.getLatlng()[0], c2.getLatlng()[0], c1.getLatlng()[1],
                        c2.getLatlng()[1]);
            }
            System.out.println("Total: " + distance);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
