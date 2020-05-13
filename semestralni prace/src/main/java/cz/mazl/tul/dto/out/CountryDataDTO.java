package cz.mazl.tul.dto.out;

import java.util.List;

public class CountryDataDTO {
    private String iso;
    private String name;
    private List<CityDTO> cityDTOList;

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityDTO> getCityDTOList() {
        return cityDTOList;
    }

    public void setCityDTOList(List<CityDTO> cityDTOList) {
        this.cityDTOList = cityDTOList;
    }

    @Override
    public String toString() {
        return "CountryDataDTO{" +
                "iso='" + iso + '\'' +
                ", name='" + name + '\'' +
                ", cityDTOList=" + cityDTOList +
                '}';
    }
}
