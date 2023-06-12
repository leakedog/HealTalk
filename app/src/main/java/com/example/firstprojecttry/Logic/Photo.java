package com.example.firstprojecttry.Logic;

public class Photo {
    public String photoURL;

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
    public Photo(String photoURL){
        this.photoURL = photoURL;
    }
    Photo(){}
}
