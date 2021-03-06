package cz.mazl.tul.dto.out;

import java.util.Date;

public class TemperatureDTO {
    private String tempId;
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

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    @Override
    public String toString() {
        return "TemperatureDTO{" +
                "tempId=" + tempId +
                ", date=" + date +
                ", temperature=" + temperature +
                '}';
    }
}
