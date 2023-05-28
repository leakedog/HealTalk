package com.example.firstprojecttry;

import static android.content.ContentValues.TAG;

import static com.example.firstprojecttry.Logic.calculate;

import static java.sql.DriverManager.println;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class uploadModel {
    public static NavController navController = null;
    public static DatabaseReference mDatabase;
    public static DatabaseReference clientsBase;
    public static DatabaseReference executorsBase;
    public static DatabaseReference orderBase;
    public static Integer maskLoaded = 0;
    public static Boolean Loaded = false;
    public static FirebaseAuth mAuth = null;
    static {
        mDatabase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        clientsBase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("users/clients/info");
        executorsBase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("users/executors/");
        orderBase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("orders/");
        clientsBase.addValueEventListener(produceClientEventListener());
        executorsBase.addValueEventListener(produceExecutorEventListener());
        orderBase.addValueEventListener(produceOrderEventListener());
        mAuth = FirebaseAuth.getInstance();
    }
    static Integer overall = 100;
    static Map<String, Integer> getIdFromToken = new HashMap<String, Integer>();
    protected static void addClient(Client person) {
       // mDatabase.child("users").child("clients").child("info").child(overall.toString()).setValue(person).addOnCompleteListener(insertionReaction("Client")).addOnFailureListener(failureReaction("Client"));
       // getIdFromToken.put(person.getToken(), overall);
       // mDatabase.child("users").child("clients").child("verify_public_key").child(overall.toString()).setValue(person.getToken());
        overall++;

    }
    /// yup this one is long, instead what we can do, is to give each of the user public key, it's number of register user, afterwards we have
    /// a database of (public_key, token), thus we just take this token and if it is actually the same than we good, otherwise we throw an error
    /// if everything okay, we know that our key on our data_base is public key, thus we can reference to it. DONE.

    protected static void updateClient(Client person){

    }

    protected static void addExecutor(Executor person) {
        println("authViewModel " + AuthViewModel.token);
       // mDatabase.child("users").child("executors").child(AuthViewModel.token).setValue(person).addOnCompleteListener(insertionReaction("Executor")).addOnFailureListener(failureReaction("Executor"));
    }
    protected static void addOrder(Order order) {
        mDatabase.child("orders").child(order.getId().toString()).setValue(order).addOnCompleteListener(insertionReaction("Order")).addOnFailureListener(failureReaction("Order"));
    }
    // load values from Client database and react for every change
    static ValueEventListener produceClientEventListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Client> list = snapshot.getValue(new GenericTypeIndicator<HashMap<String,Client>>() {
                    @Override
                    public int hashCode() {
                        return super.hashCode();
                    }
                });
                try {
                    for (Map.Entry<String, Client> pairClient : list.entrySet()) {
                        var client = pairClient.getValue();
                        Client.container.update(client.id, client);
                    }
                } catch (NullPointerException e) {
                    System.out.println("List is empty in " + e.getMessage() + " for method " + "clientUpdateDatabase");
                }
                maskLoaded |= 1;
                evaluateMask();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                maskLoaded |= 1;
                evaluateMask();
            }
        };
    }
    // load values from Executor database and react for every change
    static ValueEventListener produceExecutorEventListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Executor> list = snapshot.getValue(new GenericTypeIndicator<HashMap<String,Executor>>() {
                    @Override
                    public int hashCode() {
                        return super.hashCode();
                    }
                });
                try{
                    for (Map.Entry<String, Executor> executor : list.entrySet()) {
                        Executor.container.update(executor.getValue().getId(), executor.getValue());
                    }
                } catch (NullPointerException e) {
                    System.out.println("List is empty in " + e.getMessage() + " for method " + "executorUpdateDatabase");
                }
                maskLoaded |= 2;
                evaluateMask();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                maskLoaded |= 2;
                evaluateMask();
            }
        };

    }
    // load values from Order database and react for every change
    static ValueEventListener produceOrderEventListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Order> list = snapshot.getValue(new GenericTypeIndicator<HashMap<String,Order>>() {
                    @Override
                    public int hashCode() {
                        return super.hashCode();
                    }
                });
                try {
                    for (Map.Entry<String, Order> pair : list.entrySet()) {
                        var order = pair.getValue();
                        Order.container.update(order.id, order);
                        order.getExecutor().setRating(calculate(order.getExecutor()));
                        order.getExecutor().setRating(calculate(order.getClient()));
                    }
                } catch (NullPointerException e) {
                    System.out.println("List is empty in " + e.getMessage() + " for method " + "orderUpdateDatabase");
                }
                maskLoaded |= 4;
                evaluateMask();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                maskLoaded |= 4;
                evaluateMask();
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
    /// if all databases loaded correctly switch to Main screen
    static public void evaluateMask() {
        /*
        if (maskLoaded == 7 && !Loaded) {
            Log.w(TAG, "Loaded databases successfully!");
            navController.navigate("screen2");
            Loaded = true;
        }

         */
    }




}



