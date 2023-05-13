package com.example.firstprojecttry;

public class Wrapper<T> {
    Class<T> kind;

    Wrapper(T object) {
        
    }

    Class<T> getKind() {
        return kind;
    }
}
