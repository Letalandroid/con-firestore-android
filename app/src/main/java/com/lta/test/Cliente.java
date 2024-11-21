package com.lta.test;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Cliente extends AppCompatActivity {

    private static final String TAG = "FirestoreLog";
    TextView txtClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        txtClientes = findViewById(R.id.txtClientes);
        txtClientes.setText("Esperando...");

        getData();
    }

    private void getData() {
        final StringBuilder clientes = new StringBuilder();

        try {
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("Cliente")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            QuerySnapshot documentSnapshots = task.getResult();
                            if (documentSnapshots != null) {
                                for (QueryDocumentSnapshot document : documentSnapshots) {
                                    // Log.d(TAG, "Document ID: " + document.getId() + " => " + document.getData());
                                    clientes.append("Document ID: ").append(document.getId()).append("\n");
                                    clientes.append("Document Data: ").append(document.getData())
                                            .append("\n\n");
                                }
                            } else {
                                Log.d(TAG, "No documents found.");
                            }
                        } else {
                            Log.e(TAG, "Error getting documents: ", task.getException());
                        }

                        txtClientes.setText(clientes.toString());

                    });

        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
    }
}