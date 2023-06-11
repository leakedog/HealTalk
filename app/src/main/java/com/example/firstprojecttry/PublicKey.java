package com.example.firstprojecttry;

import java.util.HashMap;
import java.util.Map;

public class PublicKey {
    static Map<String, Integer> keys = new HashMap<String, Integer>();
    static void update(String token, Integer publicId){
        keys.put(token, publicId);
    }
    static Integer getPublicId(String token){
        return keys.get(token);
    }

}