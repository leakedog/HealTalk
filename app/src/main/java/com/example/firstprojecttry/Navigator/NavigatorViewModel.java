package com.example.firstprojecttry.Navigator;

import androidx.navigation.NavController;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NavigatorViewModel {

    @NotNull
    public static NavController getNavController() {
        return NavigatorModel.getNavController();
    }

    public static void showByRoute(@NotNull String route) {
        NavigatorModel.showByRoute(route);
    }

    public static void showChat() {
        NavigatorModel.showChat();

    }

    public static void showChatsWithId(@Nullable Integer id) {
        NavigatorModel.showChatsWithId(id);
    }
}
