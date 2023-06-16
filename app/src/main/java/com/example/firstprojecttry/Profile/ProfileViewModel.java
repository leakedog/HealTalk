package com.example.firstprojecttry.Profile;

import static com.example.firstprojecttry.Navigator.NavigatorModel.showChatsWithId;
import static com.example.firstprojecttry.Navigator.NavigatorModel.showErrorImage;
import static com.example.firstprojecttry.Navigator.NavigatorModel.showExecutorWithId;
import static com.example.firstprojecttry.Navigator.NavigatorModel.showExecutorsFromChatWithId;
import static com.example.firstprojecttry.Navigator.NavigatorModel.showProfile;

import android.net.Uri;

import androidx.compose.runtime.MutableState;
import androidx.navigation.NavController;

import com.example.firstprojecttry.Logic.Executor;
import com.example.firstprojecttry.Logic.UserType;

import org.jetbrains.annotations.NotNull;

public class ProfileViewModel {
    public static void tryUploadImage(MutableState<String> photoURL, MutableState<Uri> imageUriState) {
        ProfileModel.uploadImage(photoURL, imageUriState);
    }
    public static void onSuccessImageLoad(MutableState<String> photoURL, String imageURL) {
        photoURL.setValue(imageURL);
    }

    public static void uploadUser()  {
        ProfileModel.uploadUser();
    }

    public static void firstUploadUser(UserType Type) {
        ProfileModel.firstUploadUser(Type);
    }

    public static void onFailureImageLoad(MutableState<String> executor) {
        showErrorImage();
    }

    public static void handleComeBackFromError() {
        showProfile();
    }
    public static void showExecutor(@NotNull Executor executor){
        showExecutorWithId(executor.getId());
    }
    public static void showExecutorFromChat(@NotNull Executor executor){
        showExecutorsFromChatWithId(executor.getId());
    }

    public static void showChatWith(@NotNull Executor value) {
        showChatsWithId(value.getId());
    }
}
