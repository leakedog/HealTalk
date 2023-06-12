package com.example.firstprojecttry.Login;

import androidx.compose.runtime.MutableState;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;

import com.example.firstprojecttry.Logic.User;
import com.example.firstprojecttry.Notifications;


public class AuthViewModel {
    public static NavController navController = null;


    public static boolean isWaiting = false;
    public static boolean isWaitingLoading = false;

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
        isWaiting = false;

        if (error != null) {
            error.setValue(true);
        }
        navController.navigate("error");
    }

    public static void handleErrorRegistration(MutableState<Boolean> error) {
        isWaiting = false;
        error.setValue(true);
    }

    public static void handleSuccessfulRegistration() {
        System.out.println("NavigateToChoosing");
        navController.navigate("chooseTypeClientOrExecutor");
        AuthModel.getCurrentUserToken(null);
    }

    static void tryLogin(String email, String password, MutableState<Boolean> error) {
        AuthModel.login(email, password, error);
    }

    static void tryRegister(String email, String password, MutableState<Boolean> error) {
        AuthModel.register(email, password, error);
    }
    public static void goBackToStart() {
        navController.navigate("greeting");
    }


    public static void handleSuccessfulLogin() {
        System.out.println("LOGGED");
        isWaiting = true;
        navController.navigate("loading");
        AuthModel.getCurrentUserToken(null);
    }

    public static void handleErrorLogin(MutableState<Boolean> error) {

        isWaiting = false;
        error.setValue(true);
    }

    public static void handleToken() {
        System.out.println("Token: " + AuthModel.token);
        System.out.println(isWaiting);
        if (isWaiting) {
            isWaiting = false;
            startLogged();
        }
    }

    public static void loadToken() {
        if (AuthModel.token == null) {
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
                handleError();
                return;
            }
            Notifications.setNotificationListener(AuthViewModel.getCurrentUser().getId());
            navController.navigate("LoggedApp"); /// loggedApp
        }

    }

    public static void reactLoaded() {
        if (isWaitingLoading) {

            System.out.println("WW");
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
}
