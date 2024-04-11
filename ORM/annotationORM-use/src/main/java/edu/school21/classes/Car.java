package edu.school21.classes;

import edu.school21.HtmlForm;
import edu.school21.HtmlInput;
import edu.school21.*;

import java.util.*;

@HtmlForm(fileName = "Car.html", action = "/cars", method = "post")
@OrmEntity(table = "simple_car")
    public class Car {
    @OrmColumnId()
    private Long id;
    @HtmlInput(type = "text", name = "brand", placeholder = "Enter brand", thisClass = "Car")
    @OrmColumn(name = "brand", length = 10)
        private String brand;
    @HtmlInput(type = "text", name = "model", placeholder = "Enter model", thisClass = "Car")
    @OrmColumn(name = "model", length = 10)
        private String model;
    @HtmlInput(type = "text", name = "color", placeholder = "Enter color", thisClass = "Car")
    @OrmColumn(name = "color", length = 10)
        private String color;
    @HtmlInput(type = "checkbox", name = "operable", placeholder = "Enter operable", thisClass = "Car")
    @OrmColumn(name = "operable")
        private java.lang.Boolean operable;
    @HtmlInput(type= "number", name = "year", placeholder = "Enter year", thisClass = "Car")
    @OrmColumn(name = "year")
        private java.lang.Integer year;


        public Car() {
            this.brand = "VAZ";
            this.model = "2101";
            this.color = "white";
            this.operable = true;
            this.year = 1977;

        }

        public Car(String brand, String model, String color, boolean operable, int year) {
            this.brand = brand;
            this.model = model;
            this.color = color;
            this.operable = operable;
            this.year = year;
        }

        public boolean fix() {
            this.operable = true;
            return true;
        }

        public String ChangeColor(String newColor) {
            this.color = newColor;
            return this.color;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", edu.school21.classes.Car.class.getSimpleName() + "[", "]")
                    .add("brand='" + brand + "'")
                    .add("model='" + model + "'")
                    .add("color=" + color)
                    .add("operable='" + operable + "'")
                    .add("year=" + year)
                    .toString();
        }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public boolean isOperable() {
        return operable;
    }

    public int getYear() {
        return year;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOperable(boolean operable) {
        this.operable = operable;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
