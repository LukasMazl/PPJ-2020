package cz.mazl.tul.dto.out;

import java.util.List;

public class CityDTO {
    private String cityName;
    private List<TemperatureDTO> temperatureDTOList;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<TemperatureDTO> getTemperatureDTOList() {
        return temperatureDTOList;
    }

    public void setTemperatureDTOList(List<TemperatureDTO> temperatureDTOList) {
        this.temperatureDTOList = temperatureDTOList;
    }
}
