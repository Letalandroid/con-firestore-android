package com.lta.test;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FirestoreLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this); // Inicializa Firebase
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // Obtener la instancia de Firestore
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            // Leer los documentos de la colección "usuarios"
            db.collection("usuarios")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            QuerySnapshot documentSnapshots = task.getResult();
                            if (documentSnapshots != null) {
                                for (QueryDocumentSnapshot document : documentSnapshots) {
                                    Log.d(TAG, "Document ID: " + document.getId() + " => " + document.getData());
                                }
                            } else {
                                Log.d(TAG, "No documents found.");
                            }
                        } else {
                            Log.e(TAG, "Error getting documents: ", task.getException());
                        }
                    });

        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
    }

}
