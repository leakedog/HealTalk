package com.example.firstprojecttry;

import static android.content.ContentValues.TAG;

import static androidx.core.app.ActivityCompat.startIntentSenderForResult;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.annotation.NonNull;
import androidx.compose.runtime.MutableState;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.content.Context;

public class AuthModel {
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static BeginSignInRequest signInRequest;

    private static SignInClient oneTapClient;
    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
    private boolean showOneTapUI = true;
    public static void login(String email, String password, MutableState<Boolean> error) {
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
    public static void buildGmail() {
        if (signInRequest != null)
            return;
        Context context = getApplicationContext();
        String text = context.getString(R.string.default_web_client_id);
        oneTapClient = Identity.getSignInClient(context);
        signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(text)
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(false)
                        .build()).build();

    }

    public static void register(String email, String password, MutableState<Boolean> error) {
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
                        AuthViewModel.handleErrorRegistration(error);
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
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            System.out.println("Waiting");
            currentUser.getIdToken(true)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            String token = task.getResult().getToken();
                            // Use the token as needed
                            AuthViewModel.handleToken(token);
                        } else {
                            // Token retrieval failed, handle the error
                            Exception exception = task.getException();
                            // Handle the exception accordingly
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            AuthViewModel.handleTokenError(error);
                        }
                    });
        } else {
            System.out.println("Error");

        }
    }
    public static void startApplication() {
        AuthViewModel.showLoading();
        if (mAuth.getCurrentUser() != null) {
            getCurrentUserToken(null);
        } else {

            AuthViewModel.showGreeting();
        }

    }

    public static Logic.User getCurrentUser() {
        //TODO find really who i am
        return Logic.Executor.container.getFirst();
    }
}
