package com.example.firstprojecttry;

import androidx.compose.runtime.MutableState;
import androidx.navigation.NavController;

public class AuthViewModel {
    public static NavController navController = null;
    public static String token = null;
    static void showGreeting() {
        navController.navigate("greeting");
    }
    public static void showLogin() {
        navController.navigate("login");
    }
    static void showRegistration() {
        navController.navigate("registration");
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
        MutableState<Boolean> error = null;
        AuthModel.getCurrentUserToken(error);
    }

    static void tryLogin(String email, String password, Boolean error) {
        AuthModel.login(email, password, error);
    }

    static void tryRegister(String email, String password, MutableState<Boolean> error) {
        AuthModel.register(email, password, error);
    }

    public static void handleSuccessfulLogin() {
        navController.navigate("loading");
        MutableState<Boolean> error = null;
        AuthModel.getCurrentUserToken(error);
    }

    public static void handleErrorLogin(Boolean error) {
        error = true;
    }

    public static void handleToken(String realToken) {
        token = realToken;
        navController.navigate("loggedApp");
    }
    public static void startApplication() {
        
    }
}
