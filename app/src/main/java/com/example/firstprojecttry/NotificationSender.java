package com.example.firstprojecttry;

import static android.content.ContentValues.TAG;
import static com.example.firstprojecttry.Messenger.userChat;
import static com.example.firstprojecttry.ViewKt.getRoot;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class NotificationSender {
    static void sendMessageNotification(Integer userid, Integer chatid){
        DatabaseReference myNotification = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("notifications/"+userid);

        Random random = new Random();
        int min = 1;
        int max = 2000000000;
        int randomNumber = random.nextInt(max - min + 1) + min;
        myNotification.child("N" + randomNumber).child("NewMessage").setValue(chatid);
    }
}
