package com.example.firstprojecttry.Profile;

import static com.example.firstprojecttry.Logic.UtilityClass.descriptionMap;
import static com.example.firstprojecttry.Logic.UtilityClass.descriptionStates;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.compose.runtime.MutableState;
import com.example.firstprojecttry.Logic.Client;
import com.example.firstprojecttry.Logic.DescriptionCharacteristicField;
import com.example.firstprojecttry.Logic.Executor;
import com.example.firstprojecttry.Logic.User;
import com.example.firstprojecttry.Logic.UserType;
import com.example.firstprojecttry.Login.AuthModel;
import com.example.firstprojecttry.Login.AuthViewModel;
import com.example.firstprojecttry.Logic.PublicKey;
import com.example.firstprojecttry.Upload.uploadModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.lang.reflect.Field;
import java.util.UUID;

public class ProfileModel {

    private static FirebaseStorage storage = FirebaseStorage.getInstance();
    private static StorageReference storageRef = storage.getReference();

    public static void uploadImage(MutableState<String> photoURL, MutableState<Uri> imageUriState) {
        Uri fileUri = imageUriState.getValue();
        StorageReference randomRef = storageRef.child("images").child(UUID.randomUUID().toString());
        UploadTask uploadTask = randomRef.putFile(fileUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Handle upload success
                randomRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri downloadUri) {
                        String imageUrl = downloadUri.toString();
                        ProfileViewModel.onSuccessImageLoad(photoURL, imageUrl);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle upload failure
                ProfileViewModel.onFailureImageLoad(photoURL);


            }
        });

    }

    public static void uploadUser()  {
        if (AuthModel.token == null) {
            AuthViewModel.isWaiting = true;
            AuthViewModel.loadToken();
        }
        while (AuthModel.token == null) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }
        }
        var user = AuthModel.getCurrentUser();
        for (var x : descriptionStates.entrySet()) {
            if (x.getValue() == null) continue;
            try {
                var value = x.getValue();
                Field field = DescriptionCharacteristicField.getField(x.getKey(), user);
                field.setAccessible(true);
                var hisClass = field.getType();
                if (hisClass == Integer.class) {
                    value = Integer.valueOf((String) value);
                }
                field.set(DescriptionCharacteristicField.getObject(x.getKey(), user), hisClass.cast(value));
            }catch (Exception e) {
                System.out.println("ERROR " + e.getMessage() + " " + "WWW ? " + x.getKey());
            }
        }
        uploadModel.addUser(user);
    }

    public static void firstUploadUser(UserType type) {
        // Type == 0 Client, otherwise User
        User user;
        if (AuthModel.token == null || AuthModel.token.equals("")) {
            AuthViewModel.isWaiting = true;
            AuthViewModel.loadToken();
        }
        while (AuthModel.token == null || AuthModel.token.equals("")) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }
        }
        if (type == UserType.CLIENT) {
            user = new Client();
        } else {
            user = new Executor();
        }
        user.setToken(AuthModel.token);
        PublicKey.update(user.getToken(), user.getId());
        uploadModel.addPublicKey(user.getToken(), user.getId());


        for (var x : descriptionStates.entrySet()) {
            try {
                var value = x.getValue();
                Field field = DescriptionCharacteristicField.getField(x.getKey(), user);
                field.setAccessible(true);
                var hisClass = field.getType();
                if (hisClass == Integer.class) {
                    value = Integer.valueOf((String) value);
                }
                field.set(DescriptionCharacteristicField.getObject(x.getKey(), user), hisClass.cast(value));
            }catch (Exception e) {
                System.out.println("ERROR " + e.getMessage() + " descriptionStates " + x.getKey() + " " + x.getValue().getClass());
            }
        }

        User.container.update(user.getId(), user);
        if (type == UserType.CLIENT) {
            Client.container.update(user.getId(), (Client) user);
        } else {
            Executor.container.update(user.getId(), (Executor) user);
        }
        uploadModel.addUser(user);
        AuthViewModel.startLogged();
    }
}
