package com.example.flobiz.Model;

public class City {

    String name;

    float temp;
    float humidity;
    float pressure;

    public City(float temp, float humidity, float pressure, String name) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.name = name;
    }

    public City(String name){
        this.name = name;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
