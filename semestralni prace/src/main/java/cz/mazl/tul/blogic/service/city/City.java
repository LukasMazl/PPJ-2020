package cz.mazl.tul.blogic.service.city;

public interface City {

    /**
     * Have to return name of city
     *
     * @return
     */
    String getName();

    /**
     * Country ISO for selection of country.
     *
     * @return
     */
    String countryIso();
}
