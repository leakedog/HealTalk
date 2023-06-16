package com.example.firstprojecttry.Login;

import static com.example.firstprojecttry.Navigator.NavigatorModel.*;

import android.app.Activity;

import androidx.compose.runtime.MutableState;
import androidx.navigation.NavController;

import com.example.firstprojecttry.Logic.Executor;
import com.example.firstprojecttry.Logic.User;
import com.example.firstprojecttry.Logic.UserType;
import com.example.firstprojecttry.Notifications.Notifications;

import org.jetbrains.annotations.NotNull;


public class AuthViewModel {


    public static boolean isWaiting = false;
    public static boolean isWaitingLoading = false;


    public static void handleTokenError(MutableState<Boolean> error) {
        isWaiting = false;

        if (error != null) {
            error.setValue(true);
        }
        handleError();
    }

    public static void handleErrorRegistration(MutableState<Boolean> error,  MutableState<String> errorString, String errorStringValue) {
        isWaiting = false;
        error.setValue(true);
        errorString.setValue(errorStringValue);
    }

    public static void handleSuccessfulRegistration() {
        showChooseType();
        AuthModel.getCurrentUserToken(null);
    }

    static void tryLogin(String email, String password, MutableState<Boolean> error) {
        AuthModel.login(email, password, error);
    }

    static void tryRegister(String email, String password, MutableState<Boolean> error, MutableState<String> errorString) {
        AuthModel.register(email, password, error, errorString);
    }
    public static void goBackToStart() {
        navController.navigate("greeting");
    }


    public static void handleSuccessfulLogin() {
        isWaiting = true;
        showLoading();
        AuthModel.getCurrentUserToken(null);
    }

    public static void handleErrorLogin(MutableState<Boolean> error) {

        isWaiting = false;
        error.setValue(true);
    }

    public static void handleToken() {
        if (isWaiting) {
            isWaiting = false;
            startLogged();
        }
    }

    public static void loadToken() {
        if (AuthModel.token == null || AuthModel.token == "") {
            isWaiting = true;
            AuthModel.getCurrentUserToken(null);
        }
    }

    public static void goAsClient() {
        navController.navigate("RegisterAsClient");
    }

    public static void goAsExecutor() {
        navController.navigate("RegisterAsExecutor");
    }
    public static void handleComeBackFromError() {
        showGreeting();
    }

    public static User getCurrentUser() {
        return AuthModel.getCurrentUser();
    }

    public static void goToSignUp() {
        navController.navigate("registration");
    }

    public static void goToLogin() {
        navController.navigate("login");
    }

    public static void startLogged() {
        if (!isWaitingLoading && !isWaiting) {
            if (AuthViewModel.getCurrentUser() == null) {
                handleSuccessfulRegistration();
                return;
            }
            Notifications.setNotificationListener(AuthViewModel.getCurrentUser().getId());
            showLoggedApp();
        }

    }

    public static void reactLoaded() {
        if (isWaitingLoading) {
            isWaitingLoading = false;
            startLogged();
        }
    }

    public static void handleError() {
        navController.navigate("error");
    }

    public static void logOut() {
        AuthModel.logOut();
        goBackToStart();
    }

    @NotNull
    public static String getToken() {
        if (AuthModel.token != null)
            return AuthModel.token;
        return null;
    }


    public static void handleActivityCode(Integer resultCode) {
        if (resultCode == Activity.RESULT_OK) {
            if (AuthModel.getCurrentUser() == null) {
                handleSuccessfulRegistration();
            } else {
                handleSuccessfulLogin();
            }
        } else {
            AuthViewModel.handleError();
        }
    }

    public static void tryChangePassword(String email, MutableState<Boolean> erorr,  MutableState<Boolean> success) {
        AuthModel.changePassword(email, erorr, success);
    }

    @NotNull
    public static Boolean validateEmail(@NotNull String email) {
        return AuthModel.validateEmail(email);
    }

    public static void handleChangePasswordError(MutableState<Boolean> error) {
        error.setValue(true);
    }

    public static void handleSuccessfulChangePassword(MutableState<Boolean> success) {
        success.setValue(true);
    }

    public static void goToForgotPassword() {
        navController.navigate("forgotPassword");
    }


    public static UserType getCurrentUserType() {
        try{
            Executor e = (Executor) getCurrentUser();
            return UserType.EXECUTOR;
        } catch (Exception e) {
            return UserType.CLIENT;
        }
    }
}
