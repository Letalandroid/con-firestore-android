package com.lta.test;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Productos extends AppCompatActivity {

    private static final String TAG = "FirestoreLog";
    TextView txtProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        txtProductos = findViewById(R.id.txtProductos);
        txtProductos.setText("Esperando...");

        getData();
    }

    private void getData() {
        final StringBuilder productos = new StringBuilder();

        try {
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("Productos")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            QuerySnapshot documentSnapshots = task.getResult();
                            if (documentSnapshots != null) {
                                for (QueryDocumentSnapshot document : documentSnapshots) {
                                    // Log.d(TAG, "Document ID: " + document.getId() + " => " + document.getData());
                                    productos.append("Document ID: ").append(document.getId()).append("\n");
                                    productos.append("Document Data: ").append(document.getData())
                                            .append("\n\n");
                                }
                            } else {
                                Log.d(TAG, "No documents found.");
                            }
                        } else {
                            Log.e(TAG, "Error getting documents: ", task.getException());
                        }

                        txtProductos.setText(productos.toString());

                    });

        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
    }
}