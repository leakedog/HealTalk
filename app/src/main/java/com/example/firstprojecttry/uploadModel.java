package com.example.firstprojecttry;

import static android.content.ContentValues.TAG;

import static com.example.firstprojecttry.Logic.calculate;
import static com.example.firstprojecttry.Messenger.Chats;
import static com.example.firstprojecttry.Messenger.chatMessages;
import static com.example.firstprojecttry.Messenger.userChat;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static java.sql.DriverManager.println;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.compose.runtime.MutableState;
import androidx.navigation.NavController;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.example.firstprojecttry.Logic.*;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class uploadModel {
    public static NavController navController = null;
    public static DatabaseReference mDatabase;
    public static DatabaseReference clientsBase;
    public static DatabaseReference executorsBase;
    public static DatabaseReference orderBase;
    public static DatabaseReference chatBase;
    public static DatabaseReference userChatBase;

    public static DatabaseReference publicKeyBase;
    public static Boolean Loaded = false;

    public static void addUser(User user) {
        try {
            var executor = (Executor) user;
            addExecutor(executor);
        } catch (Exception e1) {
            try {
                var client = (Client) user;
                assert client != null;
                addClient(client);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }


    static public class LoadWait{
        final int EXPECT = 6;
        Integer maskLoaded = 0;
        Boolean isDone = false;
        void update(int id){
            if(isDone)
                return;
            maskLoaded |= (1 << id);
            System.out.println(maskLoaded + " We did it again");
            if(maskLoaded == (1 << EXPECT) - 1){
                System.out.println("we updated and we navigate\n");
//                navController.navigate("Chat");
                uploadModel.reactLoaded();
                isDone = true;
            }
        }
        Integer get(){
            return maskLoaded;
        }
    }

    public static void reactLoaded() {
        AuthViewModel.reactLoaded();
    }

    public static void setNavigation(NavController nav){
        navController = nav;
    }
    public static LoadWait loadedResources = new LoadWait();
    public static FirebaseAuth mAuth = null;
    //TODO to find myself probably make another database, which will hold key = id, value = token, when I import database, just make map(token, id).
    static {
        mDatabase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        clientsBase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("users/clients/info");
        executorsBase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("users/executors/");
        orderBase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("orders/");
        chatBase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("chat");
        userChatBase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("users/userChat");
        publicKeyBase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("users/publicKeys");
        clientsBase.addValueEventListener(produceClientEventListener());
        executorsBase.addValueEventListener(produceExecutorEventListener());
        orderBase.addValueEventListener(produceOrderEventListener());
        chatBase.addValueEventListener(produceChatEventListener());
        userChatBase.addValueEventListener(produceUserChatEventListener());
        System.out.println("listeners loaded");
        publicKeyBase.addValueEventListener(producePublicKeyEventListener());
//        clientsBase.addChildEventListener(produceClientChildListener());
        mAuth = FirebaseAuth.getInstance();
    }

    /// so far we have on verification of your public code using your token, to do this we just need to make a new database that stores (public_id, token)
    protected static void addClient(Client person) {
        mDatabase.child("users").child("clients").child("info").child("C"+person.getId().toString()).setValue(person).addOnCompleteListener(insertionReaction("Client")).addOnFailureListener(failureReaction("Client"));
    }


    /// yup this one is long, instead what we can do, is to give each of the user public key, it's number of register user, afterwards we have
    /// a database of (public_key, token), thus we just take this token and if it is actually the same than we good, otherwise we throw an error
    /// if everything okay, we know that our key on our data_base is public key, thus we can reference to it. DONE.
    protected static void addExecutor(Executor person) {
        mDatabase.child("users").child("executors").child("E"+person.getId().toString()).setValue(person).addOnCompleteListener(insertionReaction("Executor")).addOnFailureListener(failureReaction("Executor"));
    }
    protected static void addChat(Messenger.Chat chat){
        // System.out.println("TRHOW MY FUNCTION");
        mDatabase.child("users").child("userChat").child(chat.getA().toString()).child(chat.getId().toString()).setValue(chat.getId().toString()).addOnCompleteListener(insertionReaction("chat")).addOnFailureListener(failureReaction("chat"));
        mDatabase.child("users").child("userChat").child(chat.getB().toString()).child(chat.getId().toString()).setValue(chat.getId().toString()).addOnCompleteListener(insertionReaction("chat")).addOnFailureListener(failureReaction("chat"));
        mDatabase.child("chat").child(chat.getId().toString()).child("chatInfo").setValue((Messenger.ChatInfo)chat);
    }
    protected static void addMessage(Integer chatId, Messenger.Message message){
        System.out.println("ADD MESSAGE BY ME");
        assert(chatId != null);
        assert(message.getId() != null);
        mDatabase.child("chat").child(chatId.toString()).child("messages").child(message.getId().toString()).setValue(message).addOnCompleteListener(insertionReaction("messagechat")).addOnFailureListener(failureReaction("messagechat"));
    }
    protected static void addOrder(Order order) {
        mDatabase.child("orders").child(order.getId().toString()).setValue(order).addOnCompleteListener(insertionReaction("Order")).addOnFailureListener(failureReaction("Order"));
    }
    // load values from Client database and react for every change
    protected static void addPublicKey(String token, Integer publicCode){
        mDatabase.child("users").child("publicKeys").child(publicCode.toString()).setValue(token);
    }
    static ValueEventListener producePublicKeyEventListener(){
        return new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                try {

                    for(DataSnapshot pair : snapshot.getChildren()){
                        Integer publicKey = Integer.valueOf(pair.getKey());
                        PublicKey.update(pair.getValue(String.class), publicKey);
                    }



                } catch (NullPointerException e) {
                    System.out.println("List is empty in " + e.getMessage() + " for method " + "clientUpdateDatabase");
                }
                loadedResources.update(5);
                evaluateMask();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                loadedResources.update(5);
                evaluateMask();
            }
        };
    }
    static ValueEventListener produceClientEventListener() {
        return new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("ThreaName + " + currentThread().getName());
                System.out.println("ONDATACHANGE");
                Map<String, Client> list = snapshot.getValue(new GenericTypeIndicator<HashMap<String,Client>>() {
                    @Override
                    public int hashCode() {
                        return super.hashCode();
                    }
                });
                try {
                    for (Map.Entry<String, Client> pairClient : list.entrySet()) {
                        var client = pairClient.getValue();
                        System.out.println("Found client " + client);
                        Client.container.update(client.id, client);
                        User.container.update(client.id, client);
                    }
                } catch (NullPointerException e) {
                    System.out.println("List is empty in " + e.getMessage() + " for method " + "clientUpdateDatabase");
                }
                loadedResources.update(0);
                evaluateMask();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                loadedResources.update(0);
                evaluateMask();
            }
        };
    }
    // load values from Executor database and react for every change
    static ValueEventListener produceExecutorEventListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("ThreadName + " + currentThread().getName());
                Map<String, Executor> list = snapshot.getValue(new GenericTypeIndicator<HashMap<String,Executor>>() {
                    @Override
                    public int hashCode() {
                        return super.hashCode();
                    }
                });
                try{
                    for (Map.Entry<String, Executor> executor : list.entrySet())
                    {
                        System.out.println("Found client " + executor.getValue().getName() + " hah " + executor.getValue().getPhoto().getPhotoURL() + executor.getValue().getId().toString());
                        Executor.container.update(executor.getValue().getId(), executor.getValue());
                        User.container.update(executor.getValue().getId(), executor.getValue());
                    }
                } catch (NullPointerException e) {
                    System.out.println("List is empty in " + e.getMessage() + " for method " + "executorUpdateDatabase");
                }
                loadedResources.update(1);
                evaluateMask();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                loadedResources.update(1);
                evaluateMask();
            }
        };

    }
    // load values from Order database and react for every change
    static ValueEventListener produceOrderEventListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("ThreaName + " + currentThread().getName());
                Map<String, Order> list = snapshot.getValue(new GenericTypeIndicator<HashMap<String,Order>>() {
                    @Override
                    public int hashCode() {
                        return super.hashCode();
                    }
                });
                try {
                    if (list != null && !list.isEmpty()) {
                        for (Map.Entry<String, Order> pair : list.entrySet()) {
                            var order = pair.getValue();
                            Order.container.update(order.id, order);

                            order.getExecutor().setRating(calculate(order.getExecutor()));
                            order.getExecutor().setRating(calculate(order.getClient()));
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println("List is empty in " + e.getMessage() + " for method " + "orderUpdateDatabase");
                }
                loadedResources.update(2);
                evaluateMask();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                loadedResources.update(2);
                evaluateMask();
            }
        };
    }
    static ValueEventListener produceChatEventListener(){
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("ThreaName + " + currentThread().getName());
                try {


                    for(DataSnapshot chat : snapshot.getChildren()){
                        Messenger.Chat addchat = new Messenger.Chat();
                        addchat.setInfo(Objects.requireNonNull(chat.child("chatInfo").getValue(new GenericTypeIndicator<Messenger.ChatInfo>() {
                            @Override
                            public int hashCode() {
                                return super.hashCode();
                            }
                        })));

                        if(chat.child("messages").exists()) {
                            for (DataSnapshot msg : chat.child("messages").getChildren()) {
                                addchat.addMessage(msg.getValue(new GenericTypeIndicator<Messenger.Message>() {
                                    @Override
                                    public int hashCode() {
                                        return super.hashCode();
                                    }
                                }));

                            }
                        }
                        Chats.update(addchat.getId(), addchat);

                    }


                } catch (NullPointerException e) {
                    System.out.println("Something very bad happened in " + e.getMessage() + " for method " + "produceChatEventListener");
                }
                loadedResources.update(3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                loadedResources.update(3);
            }
        };
    }
    protected static OnCompleteListener<Void> insertionReaction(String tableName) {
        return new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("Insertion in database " + tableName, "Successfully inserted!");
                } else if (task.isCanceled()) {
                    Log.d("Insertion in database " + tableName, "Insertion cancelled!");
                }
            }
        };
    }
    static protected OnFailureListener failureReaction(String tableName) {
        return new OnFailureListener(){
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Insertion in database " + tableName, "Error appeared: " + e.getMessage() + "!");
            }
        };
    }
    static protected ValueEventListener produceUserChatEventListener(){
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.exists()){
                    loadedResources.update(4);
                    return;
                }
                for(DataSnapshot user : snapshot.getChildren()){
                    Integer userId = Integer.valueOf(user.getKey());
                    ArrayList<Integer> chats = new ArrayList<Integer>();
                    for(DataSnapshot singleChat : user.getChildren()){
                        if(singleChat.exists())
                            chats.add(Integer.valueOf(singleChat.getValue(String.class)));
                    }
                    System.out.println("For a user " + userId);
                    for(Integer x : chats){
                        System.out.print(x + " ");
                    }
                    userChat.update(userId, chats);
                }
                System.out.println("We LOADED USER CHAT");
                loadedResources.update(4);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                loadedResources.update(4);
                evaluateMask();
            }
        };
    }
    /// if all databases loaded correctly switch to Main screen
    static public void evaluateMask() {

    }




}


