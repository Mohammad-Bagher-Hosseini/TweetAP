package com.tweetap.entities.user;

import com.tweetap.entities.exception.user.CountryException;

import java.util.ArrayList;
import java.util.Locale;

public class Country
{
    private static Country country;
    private final ArrayList<String> countries;

    private Country()
    {
        countries = new ArrayList<>();
        String[] locales = Locale.getISOCountries();
        for(String countryCode : locales)
            countries.add(new Locale("", countryCode).getDisplayCountry());
    }

    public static Country getInstance()
    {
        if(country == null)
            country = new Country();
        return country;
    }

    public void validateCountry(String countryName) throws CountryException
    {
        for(String country : countries)
            if(countryName.equalsIgnoreCase(country))
                return;
        throw new CountryException();
    }

    public ArrayList<String> getCountries()
    {
        return countries;
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        for(String country : countries)
        {
            result.append(country);
            result.append("\n");
        }

        return result.toString();
    }
}
