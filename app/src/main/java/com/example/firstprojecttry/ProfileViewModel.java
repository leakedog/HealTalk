package com.example.firstprojecttry;

import static com.example.firstprojecttry.Logic.descriptionMap;
import static com.example.firstprojecttry.Logic.descriptionStates;

import android.net.Uri;

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

    public static void uploadUser() throws InterruptedException {
        if (AuthModel.token == null) {
            AuthViewModel.isWaiting = true;
            AuthViewModel.loadToken(AuthViewModel.getCurrentRoute());
        }
        while (AuthModel.token == null) {
            Thread.sleep(100);
        }
        var user = AuthModel.getCurrentUser();

        for (var x : descriptionStates.entrySet()) {
            try {
                var value = x.getValue();
                Field field = Logic.DescriptionCharacteristicField.getField(x.getKey(), user);
                field.setAccessible(true);
                var hisClass = field.getType();
                if (hisClass == Integer.class) {
                    value = Integer.valueOf((String) value);
                }
                field.set(Logic.DescriptionCharacteristicField.getObject(x.getKey(), user), hisClass.cast(value));
            }catch (Exception e) {
                System.out.println("ERROR " + e.getMessage() + " " + "WWW ? " + x.getKey());
            }
        }
        for (var x : descriptionMap.entrySet()) {
            try {
                Field field = Logic.DescriptionCharacteristicField.getField(x.getKey(), user);
                field.setAccessible(true);

                System.out.println("UploadExecutor fieldName "  + x.getKey() +  ": " + field.get(Logic.DescriptionCharacteristicField.getObject(x.getKey(), user)));
            }catch (Exception e) {
                System.out.println("ERROR " + e.getMessage() + " " + x.getKey());
            }
        }
        uploadModel.addUser(user);
    }

    public static void firstUploadUser(Integer Type) throws InterruptedException {
        // Type == 0 Client, otherwise User
        Logic.User user;
        if (AuthModel.token == null) {
            AuthViewModel.isWaiting = true;
            AuthViewModel.loadToken("loggedApp");
        }
        while (AuthModel.token == null) {
            Thread.sleep(100);
        }
        if (Type == 0) {
            user = new Logic.Client();
        } else {
            user = new Logic.Executor();
        }
        user.token = AuthModel.token;
        uploadModel.addPublicKey(user.token, user.id);

        System.out.println("descriptionStates Upload: " + descriptionStates);

        for (var x : descriptionStates.entrySet()) {
            try {
                var value = x.getValue();
                Field field = Logic.DescriptionCharacteristicField.getField(x.getKey(), user);
                field.setAccessible(true);
                var hisClass = field.getType();
                if (hisClass == Integer.class) {
                    value = Integer.valueOf((String) value);
                }
                field.set(Logic.DescriptionCharacteristicField.getObject(x.getKey(), user), hisClass.cast(value));
            }catch (Exception e) {
                System.out.println("ERROR " + e.getMessage() + " descriptionStates " + x.getKey() + " " + x.getValue().getClass());
            }
        }
        for (var x : descriptionMap.entrySet()) {
            try {
                Field field = Logic.DescriptionCharacteristicField.getField(x.getKey(), user);
                field.setAccessible(true);
                System.out.println("UploadExecutor fieldName "  + x.getKey() +  ": " + field.get(Logic.DescriptionCharacteristicField.getObject(x.getKey(), user)));
            }catch (Exception e) {
                System.out.println("ERROR " + e.getMessage() + " descriptionMap " + x.getKey());
            }
        }
        uploadModel.addUser(user);
        AuthViewModel.startLogged();
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
