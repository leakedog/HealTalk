package com.example.firstprojecttry.Login;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.compose.runtime.MutableState;

import com.example.firstprojecttry.Logic.User;
import com.example.firstprojecttry.Login.AuthViewModel;
import com.example.firstprojecttry.PublicKey;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class AuthModel {
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static BeginSignInRequest signInRequest;
    public static String token = null;

    public static boolean validateEmail(String email) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        }
        return true;
    }

    public static void login(String email, String password, MutableState<Boolean> error) {
        if (!validateEmail(email) || Objects.equals(password, "")) {
            AuthViewModel.handleErrorLogin(error);
            return;
        }
        // Assuming you're using Firebase Authentication

        // Check if the user is already signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            try {
                // User is not signed in, authenticate the user
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Sign-in success, continue with getting the token
                                // Retrieve the user's token as needed
                                AuthViewModel.handleSuccessfulLogin();
                            } else {
                                // Sign-in failed, handle the error
                                Exception exception = task.getException();
                                Log.w(TAG, "signInWithEmailAndPassword:failure", task.getException());
                                // Handle the exception accordingly
                                AuthViewModel.handleErrorLogin(error);
                            }
                        });
            } catch (Exception exception) {
                Log.w(TAG, "signInWithEmailAndPassword:failure", exception);
                AuthViewModel.handleErrorLogin(error);
            }
        } else {
            // User is already signed in, proceed with getting the token
            AuthViewModel.handleSuccessfulLogin();
        }
    }


    public static void register(String email, String password, MutableState<Boolean> error,  MutableState<String> errorString) {
        if(!validateEmail(email)) {
            AuthViewModel.handleErrorRegistration(error, errorString, "Incorrect email");
            return;
        }
        if (Objects.equals(password, "")) {
            AuthViewModel.handleErrorRegistration(error, errorString, "Password is empty");
            return;
        }
        try {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        AuthViewModel.handleSuccessfulRegistration();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        AuthViewModel.handleErrorRegistration(error, errorString, task.getException().getMessage());
                    }
                }
            });
        } catch (Exception e) {
            Log.w(TAG, "createUserWithEmail:failure", e);
            AuthViewModel.handleErrorLogin(error);
        }
    }
    // Method to retrieve the user's token
    public static void getCurrentUserToken(MutableState<Boolean> error) {
        System.out.println("getCurrentUserToken");
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            System.out.println("Waiting");
            token = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
            AuthViewModel.handleToken();
        } else {
            System.out.println("Error");

        }
    }
    public static void startApplication() {
        if (mAuth.getCurrentUser() != null) {
            AuthViewModel.isWaiting = true;
            AuthViewModel.isWaitingLoading = true;
            getCurrentUserToken(null);
            AuthViewModel.showLoading();
        } else {
            AuthViewModel.showGreeting();
        }

    }

    public static User getCurrentUser() {
        System.out.println(PublicKey.getPublicId(token));
        if (PublicKey.getPublicId(token) == null) {
            logOut();
            AuthViewModel.handleError();
            return null;
        }
        return User.getContainer().get(PublicKey.getPublicId(token));
    }

    public static void logOut() {
        mAuth.signOut();
    }

    public static void changePassword(String email, MutableState<Boolean> error, MutableState<Boolean> success) {
        if(!validateEmail(email)) {
            AuthViewModel.handleChangePasswordError(error);
            return;
        }

        mAuth.sendPasswordResetEmail(email);
        AuthViewModel.handleSuccessfulChangePassword(success);
    }
}
