package cz.mazl.tul.dto;

import java.util.List;
import java.util.Map;

public class IndexDataDTO {
    private Map<String, String> countryCodes;
    private String codeSelectedCountry;
    private List<CountryDataDTO> countryDataDTOList;

    public Map<String, String> getCountryCodes() {
        return countryCodes;
    }

    public void setCountryCodes(Map<String, String> countryCodes) {
        this.countryCodes = countryCodes;
    }

    public String getCodeSelectedCountry() {
        return codeSelectedCountry;
    }

    public void setCodeSelectedCountry(String codeSelectedCountry) {
        this.codeSelectedCountry = codeSelectedCountry;
    }

    public List<CountryDataDTO> getCountryDataDTOList() {
        return countryDataDTOList;
    }

    public void setCountryDataDTOList(List<CountryDataDTO> countryDataDTOList) {
        this.countryDataDTOList = countryDataDTOList;
    }
}
