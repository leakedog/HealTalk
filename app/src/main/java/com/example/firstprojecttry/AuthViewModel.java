package com.example.firstprojecttry;

import androidx.compose.runtime.MutableState;
import androidx.navigation.NavController;

public class AuthViewModel {
    public static NavController navController = null;
    public static String token = null;
    static void showGreeting() {
        navController.navigate("greeting");
    }
    static void showChat(){navController.navigate("demontt");}
    public static void showLogin() {
        navController.navigate("login");
    }
    static void showRegistration() {
        navController.navigate("registration");
    }
    static void showLoading() {
        navController.navigate("loading");
    }

    public static void handleTokenError(MutableState<Boolean> error) {
        if (error != null) {
            error.setValue(true);
        }
        navController.navigate("error");
    }

    public static void handleErrorRegistration(MutableState<Boolean> error) {
        error.setValue(true);
    }

    public static void handleSuccessfulRegistration() {
        navController.navigate("loading");
        AuthModel.getCurrentUserToken(null);
    }

    static void tryLogin(String email, String password, MutableState<Boolean> error) {
        AuthModel.login(email, password, error);
    }

    static void tryRegister(String email, String password, MutableState<Boolean> error) {
        AuthModel.register(email, password, error);
    }

    public static void handleSuccessfulLogin() {
        navController.navigate("loading");
        AuthModel.getCurrentUserToken(null);
    }

    public static void handleErrorLogin(MutableState<Boolean> error) {
        error.setValue(true);
    }

    public static void handleToken(String realToken) {
        token = realToken;
        System.out.println("Token: " + realToken);
        navController.navigate("LoggedApp"); /// loggedApp
    }


    public static void handleComeBackFromError() {
        showGreeting();
    }

    public static Logic.User getCurrentUser() {
        return AuthModel.getCurrentUser();
    }

}
