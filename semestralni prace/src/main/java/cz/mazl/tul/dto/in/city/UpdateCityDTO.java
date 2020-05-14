package cz.mazl.tul.dto.in.city;

import javax.validation.constraints.NotNull;

public class UpdateCityDTO extends CityDTO {

    @NotNull
    private String originName;

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }
}
