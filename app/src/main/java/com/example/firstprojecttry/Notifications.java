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

public class Notifications {
    public static ArrayList<DatabaseReference> mountPoints = new ArrayList<DatabaseReference>();
    public static NavController navController = null;
    static void setController(NavController x){
        navController = x;
    }
    static Boolean initialLoad = false;
    static DatabaseReference myNotification = null;



    static ChildEventListener produceNotificationEventListener(){
        return new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


                String notificationId = snapshot.getKey();
                DataSnapshot theNotification = snapshot.getChildren().iterator().next();
                String notificationType = theNotification.getKey();
                System.out.println(notificationType + " equals " + notificationType.equals("NewMessage")) ;
                if(notificationType.equals("NewMessage")){

                    Integer chatId = Integer.valueOf(theNotification.getValue(Integer.class));

                    if(getRoot(navController).equals("chats/"+chatId)){
                        navController.navigate("chats/"+chatId);
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                /// nothing?
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                /// still nothing
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                /// we do nothing
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
    }
    static void setNotificationListener(Integer id){
        myNotification = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("notifications/"+id);
        myNotification.addChildEventListener(produceNotificationEventListener());
    }

}
