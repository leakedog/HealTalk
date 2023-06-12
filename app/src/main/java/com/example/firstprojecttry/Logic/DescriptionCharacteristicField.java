package com.example.firstprojecttry.Logic;

import static com.example.firstprojecttry.Logic.UtilityClass.classToCheckList;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

public class DescriptionCharacteristicField{
    public String descriptionTitle = "";
    public String title;
    public String body;
    public String textFieldTitle;
    public Integer restrictionValue;
    public Boolean shouldBeExtended;
    public Boolean changeable;

    public DescriptionType type;



    public DescriptionCharacteristicField(String title, String body, String textFieldTitle, DescriptionType type) {
        this.title = title;
        this.body = body;
        this.textFieldTitle = textFieldTitle;
        this.restrictionValue = 40;
        this.shouldBeExtended = false;
        this.changeable = true;
        this.type = type;
    }
    public DescriptionCharacteristicField(String title, String body, String textFieldTitle, Boolean changeable, DescriptionType type) {
        this.title = title;
        this.body = body;
        this.textFieldTitle = textFieldTitle;
        this.restrictionValue = 40;
        this.shouldBeExtended = false;
        this.changeable = changeable;
        this.type = type;
    }
    public DescriptionCharacteristicField(String descriptionTitle, String title, String body, String textFieldTitle, Boolean changeable, DescriptionType type) {
        this.descriptionTitle = descriptionTitle;
        this.title = title;
        this.body = body;
        this.textFieldTitle = textFieldTitle;
        this.restrictionValue = 40;
        this.shouldBeExtended = false;
        this.changeable = changeable;
        this.type = type;
    }
    public DescriptionCharacteristicField(String descriptionTitle, String title, String body, String textFieldTitle, Integer restrictionValue, DescriptionType type) {
        this.descriptionTitle = descriptionTitle;
        this.title = title;
        this.body = body;
        this.textFieldTitle = textFieldTitle;
        this.restrictionValue = restrictionValue;
        this.shouldBeExtended = (restrictionValue >= 100);
        this.changeable = true;
        this.type = type;
    }
    public DescriptionCharacteristicField(String title, String body, String textFieldTitle, Integer restrictionValue, DescriptionType type) {
        this.title = title;
        this.body = body;
        this.textFieldTitle = textFieldTitle;
        this.restrictionValue = restrictionValue;
        this.shouldBeExtended = (restrictionValue >= 100);
        this.changeable = true;
        this.type = type;
    }
    public DescriptionCharacteristicField(String descriptionTitle, String title, String body, String textFieldTitle, Integer restrictionValue, Boolean changeable, DescriptionType type) {
        this.descriptionTitle = descriptionTitle;
        this.title = title;
        this.body = body;
        this.textFieldTitle = textFieldTitle;
        this.restrictionValue = restrictionValue;
        this.shouldBeExtended = (restrictionValue >= 100);
        this.changeable = changeable;
        this.type = type;
    }
    public DescriptionCharacteristicField(String title, String body, String textFieldTitle, Integer restrictionValue, Boolean changeable, DescriptionType type) {
        this.title = title;
        this.body = body;
        this.textFieldTitle = textFieldTitle;
        this.restrictionValue = restrictionValue;
        this.shouldBeExtended = (restrictionValue >= 100);
        this.changeable = changeable;
        this.type = type;
    }

    public static Boolean checkMyClass(Class<?> o) {
        System.out.println(o.getSimpleName());
        System.out.println(classToCheckList);
        for (Class<?> x : classToCheckList) {
            if (o == x) {
                return true;
            }
        }
        System.out.println("BAD");
        return false;
    }
    public static Object getObject(String name, Object user) {

        Class<?> currentClass = user.getClass();

        // Get fields from the current class and its superclasses
        while (currentClass != null) {
            if (checkMyClass(currentClass)) {
                try {
                    var field = currentClass.getDeclaredField(name);
                    field.setAccessible(true);
                    return user;
                } catch (Exception error) {
                    for (Field field : currentClass.getDeclaredFields()) {
                        field.setAccessible(true);
                        try {
                            var result = getObject(name, Objects.requireNonNull(field.get(user)));
                            if (result != null) {
                                return result;
                            }
                        } catch (Exception e) {
                            //Ignored
                        }
                    }
                }
            }
            currentClass = currentClass.getSuperclass();
        }

        return null;
    }
    public static Field getField(String name, Object user){

        Class<?> currentClass = user.getClass();
        // Get fields from the current class and its superclasses
        while (currentClass != null) {
            if (checkMyClass(currentClass)) {

                try {
                    var field = currentClass.getDeclaredField(name);
                    System.out.println("Field " + user + " " + field);
                    field.setAccessible(true);
                    return field;
                } catch (Exception error) {
                    for (Field field : currentClass.getDeclaredFields()) {
                        System.out.println(field);
                        field.setAccessible(true);
                        try {
                            var result = getField(name, Objects.requireNonNull(field.get(user)));
                            if (result != null) {
                                return result;
                            }
                        } catch (Exception e) {
                            //Ignored
                        }
                    }
                }
            }
            currentClass = currentClass.getSuperclass();
        }

        return null;
    }
    public static Object getFieldValue(String name, User executor) {

        try {
            Object object = getObject(name, executor);
            Field field = getField(name, executor);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            System.out.println("getFieldValue " + e.getMessage());
        }
        return null;
    }
}
