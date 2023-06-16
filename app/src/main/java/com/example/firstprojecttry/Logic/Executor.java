package com.example.firstprojecttry.Logic;

import static com.example.firstprojecttry.Logic.Rating.calculate;

public class Executor extends User implements CopyCat<Executor> {
    public static FeedContainer<Executor> container = new FeedContainer<>();
    private Schedule schedule;
    private Integer price;
    private Rating rating;
    private Side preferredSide;
    private Location location;
    public Executor() {
        description = new Description();
        price = 0;
        preferredSide = Side.BOTH;
        photo = new Photo();
        rating = calculate(this);
        id = getNextUserId();
        container.update(id, this);
        schedule = new Schedule();
    }

    public Executor(Integer id, String name, Description description, Schedule schedule,
                    Integer price, Photo photo, Side preferredSide, Location location, String token) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.schedule = schedule;
        this.price = price;
        this.photo = photo;
        this.preferredSide = preferredSide;
        this.location = location;
        this.rating = calculate(this);
        this.token = token;
        container.update(this.id, this);
    }
    public Executor(Integer id, String name, Description description, Schedule schedule,
                    Integer price, Photo photo, Side preferredSide, Location location) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.schedule = schedule;
        this.price = price;
        this.photo = photo;
        this.preferredSide = preferredSide;
        this.location = location;
        this.rating = calculate(this);
        container.update(this.id, this);
    }

    @Override
    public void copy(Executor o) {
        this.id = o.id;
        this.name = o.name;
        this.description = o.description;
        this.schedule = o.schedule;
        this.photo = o.photo;
        this.price = o.price;
        this.preferredSide = o.preferredSide;
        this.location = o.location;
    }





    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setPreferredSide(Side preferredSide) {
        this.preferredSide = preferredSide;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Side getPreferredSide() {
        return preferredSide;
    }

    public Rating getRating() {
        return rating;
    }

    public Integer getPrice() {
        return price;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Location getLocation() {
        return location;
    }

}