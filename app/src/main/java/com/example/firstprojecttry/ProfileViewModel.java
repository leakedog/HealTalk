package com.example.firstprojecttry;

import static com.example.firstprojecttry.Logic.descriptionMap;
import static com.example.firstprojecttry.Logic.descriptionStates;

import android.net.Uri;
import android.util.Log;

import androidx.compose.runtime.MutableState;
import androidx.navigation.NavController;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public class ProfileViewModel {
    public static NavController navController = null;

    public static void tryUploadImage(MutableState<String> photoURL, MutableState<Uri> imageUriState) {
        ProfileModel.uploadImage(photoURL, imageUriState);
    }
    public static void onSuccessImageLoad(MutableState<String> photoURL, String imageURL) {
        System.out.println("LOL " + imageURL);
        photoURL.setValue(imageURL);
    }

    public static void onFailureImageLoad(MutableState<String> executor) {
        navController.navigate("errorImage");
    }

    public static void handleComeBackFromError() {
        navController.navigate("profile");
    }

    public static void uploadExecutor(@NotNull Logic.Executor executor)  {
        for (var x : descriptionStates.entrySet()) {
            try {
                Field field = Logic.DescriptionCharacteristicField.getField(x.getKey(), executor);
                field.setAccessible(true);
                var hisClass = field.getType();
                field.set(Logic.DescriptionCharacteristicField.getObject(x.getKey(), executor), hisClass.cast(x.getValue()));
            }catch (Exception e) {
                System.out.println("ERROR " + e.getMessage());
            }
        }
        for (var x : descriptionMap.entrySet()) {
            try {
                Field field = Logic.DescriptionCharacteristicField.getField(x.getKey(), executor);
                field.setAccessible(true);
                System.out.println("UploadExecutor fieldName "  + x.getKey() +  ": " + field.get(Logic.DescriptionCharacteristicField.getObject(x.getKey(), executor)));
            }catch (Exception e) {
                System.out.println("ERROR " + e.getMessage());
            }
        }
        uploadModel.addExecutor(executor);
    }
    public static void showExecutor(@NotNull Logic.Executor executor){
        navController.navigate("executors/" + executor.getId());
    }
    public static void showExecutorFromChat(@NotNull Logic.Executor executor){
        navController.navigate("executorsFromChat/" + executor.getId());
    }

    public static void showChatWith(@NotNull Logic.Executor value) {
        navController.navigate("chats/" + value.getId());
    }
}
