package com.example.cristianocalicchia.run2;

public class Race {
    private int id;
    private String image;
    private String name;
    private String location;
    private String date;
    private long distance;

    //Constructor

    public Race(int id, String image, String name, String location, String date, long distance) {
        this.id=id;
        this.image=image;
        this.name=name;
        this.location=location;
        this.date=date;
        this.distance= distance;
    }

    //Getter, Setter


    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }
}
