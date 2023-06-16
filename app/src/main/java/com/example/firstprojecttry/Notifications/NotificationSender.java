package com.example.firstprojecttry.Notifications;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class NotificationSender {
    public static void sendMessageNotification(Integer userid, Integer chatid){
        DatabaseReference myNotification = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("notifications/"+userid);

        Random random = new Random();
        int min = 1;
        int max = 2000000000;
        int randomNumber = random.nextInt(max - min + 1) + min;
        myNotification.child("N" + randomNumber).child("NewMessage").setValue(chatid);
    }
}
