package io.github.soheshts;

import io.github.soheshts.model.Country;
import io.github.soheshts.util.Constants;
import io.github.soheshts.util.Util;
import org.apache.commons.math3.util.Precision;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    private static final Long populationLimit = 6700L;

    public static void main(String[] args) {
        List<Country> countryList;
        List<Country> populationList;
        List<Country> oneCurrencyList;
        List<Country> manyCurrencyList;
        List<Country> finalList = new ArrayList<>();
        Set<String> yesCurrencySet = new HashSet<>();
        Set<String> noCurrencySet = new HashSet<>();
//        BigDecimal distance = new BigDecimal(0);
        double distance = 0.0;
        try {
            System.out.println("Getting CountryList");
            countryList = Util.readJsonFromUrl(Constants.jsonURL);
//            Find repeated currencies i.e used by other countries
            for (Country country : countryList) {
                for (int i = 0; i < country.getCurrencies().size(); i++) {

                    if (!yesCurrencySet.contains(country.getCurrencies().get(i).getName())) {
                        yesCurrencySet.add(country.getCurrencies().get(i).getName());
                    } else {
                        noCurrencySet.add(country.getCurrencies().get(i).getName());
                    }
                }
            }
            System.out.println("Got CountryList");
            populationList = countryList.stream().filter(country -> country.getPopulation() >= populationLimit).collect(Collectors.toList());
            oneCurrencyList = populationList.stream().filter(country -> country.getCurrencies().size() == 1).collect(Collectors.toList());
            manyCurrencyList = populationList.stream().filter(country -> country.getCurrencies().size() > 1).collect(Collectors.toList());
            for (Country country : oneCurrencyList) {
                if (!noCurrencySet.contains(country.getCurrencies().get(0).getName())) {
                    finalList.add(country);
                }
            }
            for (Country country : manyCurrencyList) {
                for (int i = 0; i < country.getCurrencies().size(); i++) {

                    if (!noCurrencySet.contains(country.getCurrencies().get(i).getName())) {
                        finalList.add(country);
                    }
                }
            }
            Collections.sort(finalList, (c1, c2) -> c1.getPopulation().compareTo(c2.getPopulation()));

//            Collections.reverse(populationList);
            finalList.subList(20, finalList.size()).clear();
            System.out.println(finalList.toString());
            for (int i = 0; i < finalList.size() - 1; i++) {
                Country c1 = finalList.get(i);
                Country c2 = finalList.get(i + 1);
                System.out.print(c1.getName() + "( " + c1.getLatlng()[0] + " , " + c1.getLatlng()[1] + " )"
                        + " and " + c2.getName() + "( " + c2.getLatlng()[0] + " , " + c2.getLatlng()[1] + " )" + " : ");
                distance = distance + Util.distance(c1.getLatlng()[0], c2.getLatlng()[0], c1.getLatlng()[1],
                        c2.getLatlng()[1]);

            }
            System.out.println("Total: " + Precision.round(distance, 2));
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
