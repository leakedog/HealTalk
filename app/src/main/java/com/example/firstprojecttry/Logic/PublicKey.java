package com.example.firstprojecttry.Logic;

import java.util.HashMap;
import java.util.Map;

public class PublicKey {
    public static Map<String, Integer> getKeys() {
        return keys;
    }

    public static void setKeys(Map<String, Integer> keys) {
        PublicKey.keys = keys;
    }

    static Map<String, Integer> keys = new HashMap<String, Integer>();
    public static void update(String token, Integer publicId){
        keys.put(token, publicId);
    }
    public static Integer getPublicId(String token){
        return keys.get(token);
    }

}