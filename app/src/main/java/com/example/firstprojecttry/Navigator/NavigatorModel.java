package com.example.firstprojecttry.Navigator;

import androidx.navigation.NavController;

public class NavigatorModel {
    public static NavController navController = null;
    public static void showGreeting() {
        navController.navigate("greeting");
    }
    public static void showChat(){navController.navigate("chat");}
    public static void showLogin() {
        navController.navigate("login");
    }
    public static void showRegistration() {navController.navigate("registration");}
    public static void showLoading() {
        navController.navigate("loading");
    }
    public static void showChooseType() {navController.navigate("chooseTypeClientOrExecutor");}
    public static void showLoggedApp() {navController.navigate("LoggedApp");}
    public static void showErrorImage() {navController.navigate("errorImage");}
    public static void showProfile() { navController.navigate("profile");}

    public  static void showExecutorWithId(Integer id) {
        navController.navigate("executors/" + id);
    }

    public static void showExecutorsFromChatWithId (Integer id) {
        navController.navigate("executorsFromChat/" + id);
    }

    public static void showChatsWithId(Integer id) {
        navController.navigate("chats/" + id);
    }

    public static String getRoot() {
        if (navController.getCurrentBackStackEntry() == null || navController.getCurrentBackStackEntry().getDestination() == null) {
            return null;
        }
        return navController.getCurrentBackStackEntry().getDestination().getRoute();
    }

    public static Integer getArg() {
        if (navController.getCurrentBackStackEntry() == null || navController.getCurrentBackStackEntry().getArguments() == null)
            return null;
        return navController.getCurrentBackStackEntry().getArguments().getInt("userId");
    }


    public static void showChatWithId(Integer arg) {
        navController.navigate("chats/"+arg);
    }

    public static NavController getNavController() {
        return navController;
    }

    public static void showByRoute(String route) {
        navController.navigate(route);
    }
}
