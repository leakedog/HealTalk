package com.example.firstprojecttry;

import static android.content.ContentValues.TAG;

import static com.example.firstprojecttry.Logic.calculate;

import android.net.Uri;
import android.util.Log;

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

public class ServerLogic {
    public static NavController navController = null;
    public static DatabaseReference mDatabase;
    public static DatabaseReference clientsBase;
    public static DatabaseReference executorsBase;
    public static DatabaseReference orderBase;
    public static Integer maskLoaded = 0;
    public static Boolean Loaded = false;
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
    // load values from Client database and react for every change
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

    public static void uploadImage(MutableState<Uri> imageUriState) {
        Uri fileUri = imageUriState.getValue();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference randomRef = storageRef.child("images").child(UUID.randomUUID().toString());
        UploadTask uploadTask = randomRef.putFile(fileUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Handle upload success
                System.out.println("Handle");
                randomRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri downloadUri) {

                        String imageUrl = downloadUri.toString();
                        System.out.println(imageUrl);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle upload failure
            }
        });

    }

    public static void Login() {
        // Assuming you're using Firebase Authentication

        FirebaseAuth auth = FirebaseAuth.getInstance();

        // Check if the user is already signed in
        FirebaseUser currentUser = auth.getCurrentUser();
        System.out.println(currentUser);
        if (currentUser == null) {
            // User is not signed in, authenticate the user
            auth.signInWithEmailAndPassword("user@example.com", "password")
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign-in success, continue with getting the token
                                // Retrieve the user's token as needed
                                getCurrentUserToken();
                            } else {
                                // Sign-in failed, handle the error
                                Exception exception = task.getException();
                                // Handle the exception accordingly
                            }
                        }
                    });
        } else {
            // User is already signed in, proceed with getting the token
            getCurrentUserToken();
        }
    }
    // Method to retrieve the user's token
    private static void getCurrentUserToken() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            currentUser.getIdToken(true)
                    .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                        @Override
                        public void onComplete(@NonNull Task<GetTokenResult> task) {
                            if (task.isSuccessful()) {
                                String token = task.getResult().getToken();
                                // Use the token as needed
                            } else {
                                // Token retrieval failed, handle the error
                                Exception exception = task.getException();
                                // Handle the exception accordingly
                            }
                        }
                    });
        }
    }

}



