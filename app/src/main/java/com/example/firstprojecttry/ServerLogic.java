package com.example.firstprojecttry;

import static android.content.ContentValues.TAG;

import static com.example.firstprojecttry.Logic.calculate;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.firstprojecttry.Logic.*;

public class ServerLogic {
    public DatabaseReference mDatabase;
    public DatabaseReference clientsBase;
    public DatabaseReference executorsBase;
    public DatabaseReference orderBase;
    ServerLogic() {
        mDatabase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        clientsBase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("users/clients");
        executorsBase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("users/clients");
        orderBase = FirebaseDatabase.getInstance("https://healtalk-7ab6c-default-rtdb.europe-west1.firebasedatabase.app/").getReference("orders");
        clientsBase.addValueEventListener(produceClienEventListener());
        executorsBase.addValueEventListener(produceExecutorEventListener());
        orderBase.addValueEventListener(produceOrderEventListener());
    }
    protected void saveClient(Client person) {
        mDatabase.child("users").child("clients").child(person.getId().toString()).setValue(person);
    }
    protected void saveExecutor(Executor person) {
        mDatabase.child("users").child("executors").child(person.getId().toString()).setValue(person);
        System.out.println("Success!");
    }
    protected void saveOrder(Order order) {
        mDatabase.child("orders").child(order.getId().toString()).setValue(order);
    }
    ValueEventListener produceClienEventListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Client change = snapshot.getValue(Client.class);
                if (change == null) {
                    return;
                }
                Client.container.update(change.id, change);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
    }
    ValueEventListener produceExecutorEventListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Executor change = snapshot.getValue(Executor.class);
                if (change == null) {
                    return;
                }
                Executor.container.update(change.id, change);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
    }
    ValueEventListener produceOrderEventListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Order change = snapshot.getValue(Order.class);
                if (change == null) {
                    return;
                }
                Order.container.update(change.getId(), change);
                change.getClient().setRating(calculate(change.getClient()));
                change.getExecutor().setRating(calculate(change.getExecutor()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
    }

}
