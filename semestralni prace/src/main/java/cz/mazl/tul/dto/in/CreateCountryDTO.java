package cz.mazl.tul.dto.in;

import java.util.List;

public class CreateCountryDTO {
    private String iso;
    private String countryName;
    private List<String> cityDTOList;

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<String> getCityDTOList() {
        return cityDTOList;
    }

    public void setCityDTOList(List<String> cityDTOList) {
        this.cityDTOList = cityDTOList;
    }
}
