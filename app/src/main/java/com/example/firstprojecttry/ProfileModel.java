package com.example.firstprojecttry;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.compose.runtime.MutableState;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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

}
