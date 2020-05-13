package cz.mazl.tul.blogic.provider.weather.historical;

import cz.mazl.tul.blogic.provider.weather.current.WeatherData;

import java.util.List;

public class HistoricalWeatherData {
    private String message;
    private int cod;
    private int city_id;
    private double calctime;
    private int cnt;
    private List<WeatherData> list;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public double getCalctime() {
        return calctime;
    }

    public void setCalctime(double calctime) {
        this.calctime = calctime;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<WeatherData> getList() {
        return list;
    }

    public void setList(List<WeatherData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "HistoricalWeatherData{" +
                "message='" + message + '\'' +
                ", cod=" + cod +
                ", city_id=" + city_id +
                ", calctime=" + calctime +
                ", cnt=" + cnt +
                ", list=" + list +
                '}';
    }
}
