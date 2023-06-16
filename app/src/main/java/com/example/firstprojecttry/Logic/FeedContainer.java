package com.example.firstprojecttry.Logic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FeedContainer <T> {
    public Map<Integer, T> container;
    public FeedContainer() {
        container = new HashMap<>();
    }
    public void update(int id, T value) {
        container.put(id, value);
    }
    public Integer getSize() {
        return container.size();
    }
    public T get(Integer i) {
        return container.get(i);
    }
    public T getFirst(){
        if(container.isEmpty())
            return null;
        Iterator<Integer> it = container.keySet().iterator();
        return this.get(it.next());
    }
}