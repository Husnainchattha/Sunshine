package com.example.hussnain.sunshine;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by hussnain on 3/8/18.
 */

public class WeatherInformation implements Serializable{
    private double maxTemp;
    private double minTemp;
    private String description;
    private double pressure;
    private double humidity;
    private double wind;
    private String location;
    private String dayofweek;
    private  int image;
    private long date;
    public WeatherInformation(double maxTemp, double minTemp, String description, double pressure, double humidity, double wind, String location,String dayofweek,int image,long date) {
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.description = description;
        this.pressure = pressure;
        this.humidity = humidity;
        this.wind = wind;
        this.location = location;
        this.dayofweek=dayofweek;
        this.image=image;
        this.date=date;
    }
    public WeatherInformation(){}

    public  double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public String  getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
