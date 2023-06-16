package com.example.firstprojecttry.Notifications;

import static android.content.ContentValues.TAG;



import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;

import com.example.firstprojecttry.Navigator.NavigatorModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Notifications {
    public static ArrayList<DatabaseReference> mountPoints = new ArrayList<DatabaseReference>();
    static Boolean initialLoad = false;
    static DatabaseReference myNotification = null;



    static ChildEventListener produceNotificationEventListener(){
        return new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


                String notificationId = snapshot.getKey();
                DataSnapshot theNotification = snapshot.getChildren().iterator().next();
                String notificationType = theNotification.getKey();
                if(notificationType.equals("NewMessage")){

                    Integer chatId = Integer.valueOf(theNotification.getValue(Integer.class));

                    if(NavigatorModel.getRoot().equals("chats/{userId}")){
                            NavigatorModel.showChatWithId(NavigatorModel.getArg());
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
    public static void setNotificationListener(Integer id){
        myNotification = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("notifications/"+id);
        myNotification.addChildEventListener(produceNotificationEventListener());
    }

}
