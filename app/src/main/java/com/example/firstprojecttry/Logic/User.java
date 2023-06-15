package com.example.firstprojecttry.Logic;

import com.example.firstprojecttry.FeedContainer;

public class User{

    static private Integer lastUserId = 0;

    public static FeedContainer<User> container = new FeedContainer<User>();
    protected String token;
    protected Photo photo;

    protected Description description;

    protected String name;

    protected int id;

    User() {
        token = new String();
        photo = new Photo();
        description = new Description();
        name = new String();
    }
    public static FeedContainer<User> getContainer() {
        return container;
    }

    public void copy(User from){
        this.token = from.token;
        this.id = from.id;
    }

    static public Integer getNextUserId(){
        while(User.container.get(lastUserId) != null)
            lastUserId++;
        return lastUserId;
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
        this.token = token;
        System.out.println("setToken");
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

}