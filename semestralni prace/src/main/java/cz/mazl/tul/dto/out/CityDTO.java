package cz.mazl.tul.dto.out;

import java.util.Date;
import java.util.List;

public class CityDTO {
    private String cityName;
    private List<TemperatureDTO> temperatureDTOList;
    private Date lastTemperatureUpdate;
    private Date created;

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

    public Date getLastTemperatureUpdate() {
        return lastTemperatureUpdate;
    }

    public void setLastTemperatureUpdate(Date lastTemperatureUpdate) {
        this.lastTemperatureUpdate = lastTemperatureUpdate;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
