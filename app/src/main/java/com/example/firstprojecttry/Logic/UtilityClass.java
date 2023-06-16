package com.example.firstprojecttry.Logic;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilityClass {
    public static List<Class<?>> classToCheckList = List.of(Executor.class, User.class, Schedule.class, Description.class, Photo.class, Client.class, Location.class);

    public static Map<String, DescriptionCharacteristicField> descriptionMap = new HashMap<>();
    public static Map<String, Object> descriptionStates = new HashMap<>();

    public static List<String> descriptionNames = new ArrayList<>();
    public static List<String> clientDescriptionNames = new ArrayList<>();
    public static List<String> executorDescriptionNames = new ArrayList<>();


    static{

        descriptionMap.put("name",
                new DescriptionCharacteristicField("Legal name", "Tell us your legal name",
                        "Note that this name should match your document information",
                        "What's your name:", false, DescriptionType.STRING));
        descriptionNames.add("name");
        descriptionMap.put("aboutYou",
                new DescriptionCharacteristicField("About me", "About you",
                        "The information you share will be used across our service to help other users get to know you",
                        "Write about yourself:", 400, DescriptionType.STRING));
        descriptionNames.add("aboutYou");

        descriptionMap.put("dateBirth",
                new DescriptionCharacteristicField("Date of birth", "Your date birth",
                        "You won't be able to change it after",
                        "Birth date:", 40,  false, DescriptionType.DATE));
        descriptionNames.add("dateBirth");

        descriptionMap.put("sex",
                new DescriptionCharacteristicField("Sex", "Your sex",
                        "You won't be able to change it after",
                        "Sex:", 40,  false, DescriptionType.CHECKBOX));
        descriptionNames.add("sex");

        descriptionMap.put("childNumber",
                new DescriptionCharacteristicField("Number of children", "Do you have children?",
                        "The information you share will be used across our service to help others get to know you and make our service more efficient",
                        "Number of children:", 40,  true, DescriptionType.NUMBER));
        descriptionNames.add("childNumber");

        descriptionMap.put("price",
                new DescriptionCharacteristicField("Chosen price", "Choose price that you want to charge",
                        "You should select price that you want to charge for your services. To start faster, we advise you to choose it near the average: 15$ ",
                        "Price", true, DescriptionType.NUMBER));
        descriptionNames.add("price");

        descriptionMap.put("photoURL",
                new DescriptionCharacteristicField("Choose your photo",
                        "By providing your photo, we can create a more personalized and engaging experience tailored specifically to you.",
                        "Your photo:", 0, false, DescriptionType.PHOTO));
        descriptionNames.add("photoURL");

        descriptionMap.put("scheduleMap",
                new DescriptionCharacteristicField("Choose your schedule",
                        "You should select your free time, so that we will be able to find client for you",
                        "Skip", true, DescriptionType.SCHEDULE));
        descriptionNames.add("scheduleMap");

        descriptionMap.put("location",
                new DescriptionCharacteristicField("Choose your location",
                        "You should select your location",
                        "Skip", true, DescriptionType.LOCATION));
        descriptionNames.add("location");

        {
            Executor e = new Executor();
            Client c = new Client();

            for (String name : descriptionNames) {
                if (DescriptionCharacteristicField.getField(name, e) != null) {
                    executorDescriptionNames.add(name);
                }
                if (DescriptionCharacteristicField.getField(name, c) != null) {
                    clientDescriptionNames.add(name);
                }
            }

            for (var x : descriptionMap.entrySet()) {
                try {
                    Field field = DescriptionCharacteristicField.getField(x.getKey(), e);
                    field.setAccessible(true);
                }catch (Exception er) {
                    System.out.println("ERROR " + er.getMessage());
                }
            }
        }

    }
}
