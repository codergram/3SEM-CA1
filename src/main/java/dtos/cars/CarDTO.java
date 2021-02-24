/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos.cars;

import entities.cars.Car;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Tweny
 */
public class CarDTO {
    
    private long id;
    private String brand;
    private String model;
    private int year;
    private long price;

    public CarDTO(Car car) {
        this.id = car.getId() != null ? car.getId() : -1;
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.year = car.getYear();
        this.price = car.getPrice();
    }

    public CarDTO(long id, String brand, String model, int year, long price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public CarDTO(String brand, String model, int year, long price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
    
    public static List<CarDTO> convertMovieListToDTO(List<Car> cars){
        List<CarDTO> carDTOs = cars.stream().map(currentCar -> new CarDTO(currentCar)).collect(Collectors.toList());
        return carDTOs;
    }
    
    
    
}
