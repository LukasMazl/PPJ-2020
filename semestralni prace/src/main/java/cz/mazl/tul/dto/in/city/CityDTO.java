package cz.mazl.tul.dto.in.city;

import cz.mazl.tul.blogic.service.city.City;

public class CityDTO implements City {
    private String name;
    private String countryIso;


    public void setName(String name) {
        this.name = name;
    }

    public void setCountryIso(String countryIso) {
        this.countryIso = countryIso;
    }

    public String getCountryIso() {
        return countryIso;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String countryIso() {
        return countryIso;
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "name='" + name + '\'' +
                ", countryIso='" + countryIso + '\'' +
                '}';
    }
}
