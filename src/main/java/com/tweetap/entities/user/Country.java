package com.tweetap.entities.user;

import com.tweetap.entities.exception.user.CountryException;

import java.util.ArrayList;
import java.util.Locale;

public class Country
{
    private static Country country;
    private ArrayList<String> countries;

    private Country()
    {
//        countries.add("Iran");
//        countries.add("Afghanistan");
//        countries.add("Albania");
//        countries.add("Algeria");
//        countries.add("Andorra");
//        countries.add("Angola");
//        countries.add("Antigua and Barbuda");
//        countries.add("Argentina");
//        countries.add("Armenia");
//        countries.add("Australia");
//        countries.add("Austria");
//        countries.add("Azerbaijan");
//        countries.add("Bahamas");
//        countries.add("Bahrain");
//        countries.add("Bangladesh
//                countries.add("Barbados
//                        countries.add("Belarus
//                Belgium
//        Belize
//                Benin
//        Bhutan
//                Bolivia
//        Bosnia and Herzegovina
//            Botswana
//        Brazil
//                Brunei
//        Bulgaria
//        Burkina Faso
//        Burundi
//        Cabo Verde
//        Cambodia
//                Cameroon
//        Canada
//        Central African Republic
//            Chad
//        Chile
//                China
//        Colombia
//                Comoros
//        Congo(Congo - Brazzaville)
//        Costa Rica
//        Croatia
//                Cuba
//        Cyprus
//        Czechia(Czech Republic)
//        Democratic Republic of the Congo
//            Denmark
//        Djibouti
//                Dominica
//        Dominican Republic
//        East Timor (Timor - Leste)
//        Ecuador
//                Egypt
//        El Salvador
//        Equatorial Guinea
//        Eritrea
//                Estonia
//        Eswatini(fmr."Swaziland")
//        Ethiopia
//                Fiji
//        Finland
//                France
//        Gabon
//                Gambia
//        Georgia
//                Germany
//        Ghana
//                Greece
//        Grenada
//                Guatemala
//        Guinea
//        Guinea - Bissau
//        Guyana
//                Haiti
//        Honduras
//                Hungary
//        Iceland
//                India
//        Indonesia
//                Iran
//        Iraq
//                Ireland
//        Israel
//                Italy
//        Jamaica
//                Japan
//        Jordan
//                Kazakhstan
//        Kenya
//                Kiribati
//        Korea, North(North Korea)
//        Korea, South(South Korea)
//        Kosovo
//                Kuwait
//        Kyrgyzstan
//                Laos
//        Latvia
//                Lebanon
//        Lesotho
//                Liberia
//        Libya
//                Liechtenstein
//        Lithuania
//                Luxembourg
//        Madagascar
//                Malawi
//        Malaysia
//                Maldives
//        Mali
//                Malta
//        Marshall Islands
//        Mauritania
//                Mauritius
//        Mexico
//                Micronesia
//        Moldova
//                Monaco
//        Mongolia
//                Montenegro
//        Morocco
//                Mozambique
//        Myanmar(formerly Burma)
//        Namibia
//                Nauru
//        Nepal
//                Netherlands
//        New Zealand
//        Nicaragua
//                Niger
//        Nigeria
//        North Macedonia (formerly Macedonia)
//        Norway
//                Oman
//        Pakistan
//                Palau
//        Panama
//        Papua New Guinea
//            Paraguay
//        Peru
//                Philippines
//        Poland
//                Portugal
//        Qatar
//                Romania
//        Russia
//                Rwanda
//        Saint Kitts and Nevis
//        Saint Lucia
//        Saint Vincent and the Grenadines
//            Samoa
//        San Marino
//        Sao Tome and Principe
//        Saudi Arabia
//        Senegal
//                Serbia
//        Seychelles
//        Sierra Leone
//        Singapore
//                Slovakia
//        Slovenia
//        Solomon Islands
//        Somalia
//        South Africa
//        South Sudan
//        Spain
//        Sri Lanka
//        Sudan
//                Suriname
//        Sweden
//                Switzerland
//        Syria
//                Taiwan
//        Tajikistan
//                Tanzania
//        Thailand
//                Togo
//        Tonga
//        Trinidad and Tobago
//            Tunisia
//        Turkey
//                Turkmenistan
//        Tuvalu
//                Uganda
//        Ukraine
//        United Arab Emirates
//        United Kingdom
//        United States of America
//        Uruguay
//                Uzbekistan
//        Vanuatu
//        Vatican City (Holy See)
//        Venezuela
//                Vietnam
//        Yemen
//                Zambia
//        Zimbabwe
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
