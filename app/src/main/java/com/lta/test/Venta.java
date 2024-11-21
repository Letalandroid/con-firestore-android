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

public class Venta extends AppCompatActivity {

    private static final String TAG = "FirestoreLog";
    TextView txtVenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

        txtVenta = findViewById(R.id.txtVenta);
        txtVenta.setText("Esperando...");

        getData();
    }

    private void getData() {
        final StringBuilder ventas = new StringBuilder();

        try {
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("Venta")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            QuerySnapshot documentSnapshots = task.getResult();
                            if (documentSnapshots != null) {
                                for (QueryDocumentSnapshot document : documentSnapshots) {
                                    // Log.d(TAG, "Document ID: " + document.getId() + " => " + document.getData());
                                    ventas.append("Document ID: ").append(document.getId()).append("\n");
                                    ventas.append("Document Data: ").append(document.getData())
                                            .append("\n\n");
                                }
                            } else {
                                Log.d(TAG, "No documents found.");
                            }
                        } else {
                            Log.e(TAG, "Error getting documents: ", task.getException());
                        }

                        txtVenta.setText(ventas.toString());

                    });

        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
    }
}