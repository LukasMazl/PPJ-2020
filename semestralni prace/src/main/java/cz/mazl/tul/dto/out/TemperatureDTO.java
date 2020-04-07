package cz.mazl.tul.dto.out;

import java.util.Date;

public class TemperatureDTO {
    private Date date;
    private int temperature;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
