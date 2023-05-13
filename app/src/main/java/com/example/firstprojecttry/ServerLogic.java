package com.example.firstprojecttry;

import static android.content.ContentValues.TAG;

import static com.example.firstprojecttry.Logic.calculate;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.example.firstprojecttry.Logic.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServerLogic {

    public static DatabaseReference mDatabase;
    public static DatabaseReference clientsBase;
    public static DatabaseReference executorsBase;
    public static DatabaseReference orderBase;
    static {
        mDatabase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        clientsBase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("users/clients/");
        executorsBase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("users/executors/");
        orderBase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("orders/");
        clientsBase.addValueEventListener(produceClientEventListener());
        executorsBase.addValueEventListener(produceExecutorEventListener());
        orderBase.addValueEventListener(produceOrderEventListener());
    }
    protected static void addClient(Client person) {

        mDatabase.child("users").child("clients").child(person.getId().toString()).setValue(person).addOnCompleteListener(insertionReaction("Client")).addOnFailureListener(failureReaction("Client"));
    }

    protected static void addExecutor(Executor person) {
        mDatabase.child("users").child("executors").child(person.getId().toString()).setValue(person).addOnCompleteListener(insertionReaction("Executor")).addOnFailureListener(failureReaction("Executor"));
    }
    protected static void addOrder(Order order) {
        mDatabase.child("orders").child(order.getId().toString()).setValue(order).addOnCompleteListener(insertionReaction("Order")).addOnFailureListener(failureReaction("Order"));
    }
    static ValueEventListener produceClientEventListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Client> list = snapshot.getValue(new GenericTypeIndicator<List<Client>>() {
                    @Override
                    public int hashCode() {
                        return super.hashCode();
                    }
                });
                try {
                    for (Client client : list) {
                        Client.container.update(client.id, client);
                    }
                } catch (NullPointerException e) {
                    System.out.println("List is empty in " + e.getMessage() + " for method " + "clientUpdateDatabase");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
    }
    static ValueEventListener produceExecutorEventListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Executor> list = snapshot.getValue(new GenericTypeIndicator<List<Executor>>() {
                    @Override
                    public int hashCode() {
                        return super.hashCode();
                    }
                });
                try{
                    for (Executor executor : list) {
                        Executor.container.update(executor.id, executor);
                    }
                } catch (NullPointerException e) {
                    System.out.println("List is empty in " + e.getMessage() + " for method " + "executorUpdateDatabase");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };

    }
    static ValueEventListener produceOrderEventListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Order> list = snapshot.getValue(new GenericTypeIndicator<List<Order>>() {
                    @Override
                    public int hashCode() {
                        return super.hashCode();
                    }
                });
                try {
                    for (Order order : list) {
                        Order.container.update(order.id, order);
                        order.getExecutor().setRating(calculate(order.getExecutor()));
                        order.getExecutor().setRating(calculate(order.getClient()));
                    }
                } catch (NullPointerException e) {
                    System.out.println("List is empty in " + e.getMessage() + " for method " + "orderUpdateDatabase");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
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
}

