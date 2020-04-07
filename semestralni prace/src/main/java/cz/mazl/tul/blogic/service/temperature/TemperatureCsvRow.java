package cz.mazl.tul.blogic.service.temperature;

import java.util.Date;

public class TemperatureCsvRow {
    private Date date;
    private int value;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TemperatureCsvRow{" +
                "date=" + date +
                ", value=" + value +
                '}';
    }
}
