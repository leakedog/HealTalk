package com.example.firstprojecttry.Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule {
    public Integer prop;
    public String ids;
    public Map<String, Boolean> scheduleMap;
    public static List<String> keyNames;
    static {
        keyNames = new ArrayList<>();
        String []days = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
        String []times = {"Morning", "Afternoon", "Evening", "Night"};
        for (String time : times) {
            for (String day : days) {
                keyNames.add(time + " + " + day);
            }
        }
    }
    Schedule() {

        scheduleMap = new HashMap<>();
        String []days = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
        String []times = {"Morning", "Afternoon", "Evening", "Night"};
        for (String time : times) {
            for (String day : days) {
                scheduleMap.put(time + " + " + day, false);
            }
        }
        ids = "";
        prop = 3;
    }
}