package com.example.firstprojecttry.Logic;

import static com.example.firstprojecttry.Logic.Rating.calculate;

import com.example.firstprojecttry.FeedContainer;


public class Client extends User implements CopyCat<Client> {
    public static FeedContainer<Client> container = new FeedContainer<Client>();

    private Side preferredSide;
    private Rating rating;
    public Client() {
        name = "";
        description = new Description();
        preferredSide = Side.BOTH;
        photo = new  Photo();
        rating = calculate(this);
        id = getNextUserId();
        container.update(id, this);
    }

    Client(String Name,  Description description, Photo photo, Side preferredSide, String token) {
        this.name = Name;
        this.description = description;
        this.preferredSide = preferredSide;
        this.rating = calculate(this);
        this.id = getNextUserId();
        this.token = token;
        this.photo = photo;
        container.update(this.id, this);
    }
    Client(String Name, Description description, Side preferredSide, String token) {
        this.name = Name;
        this.description = description;
        this.preferredSide = preferredSide;
        this.rating = calculate(this);
        this.id = getNextUserId();
        this.token = token;
        container.update(this.id, this);
    }



    @Override
    public void copy(Client change) {
        this.id = change.id;
        this.description = change.description;
        this.photo = change.photo;
        this.preferredSide = change.preferredSide;
        this.rating = change.rating;
    }

    public Photo getPhoto(){
        return this.photo;
    }
    public void setPhoto(Photo x){
        this.photo = x;
    }
    public String getToken(){
        return token;
    }
    public void setToken(String token){
        this.token = this.token;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public String getName(){return this.name;}
    public void setName(String s){this.name = s;}

    public Description getDescription() {
        return description;
    }
    public void setDescription(Description description) {this.description = description;}


    public Side getPreferredSide() {
        return preferredSide;
    }



    public Rating getRating() {
        return rating;
    }


    public void setPreferredSide(Side preferredSide) {
        this.preferredSide = preferredSide;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

}