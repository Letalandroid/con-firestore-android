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

public class TipoProducto extends AppCompatActivity {

    private static final String TAG = "FirestoreLog";
    TextView txtTipoProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_producto);

        txtTipoProducto = findViewById(R.id.txtTipoProducto);
        txtTipoProducto.setText("Esperando...");

        getData();
    }

    private void getData() {
        final StringBuilder t_producto = new StringBuilder();

        try {
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("Tipo_Producto")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            QuerySnapshot documentSnapshots = task.getResult();
                            if (documentSnapshots != null) {
                                for (QueryDocumentSnapshot document : documentSnapshots) {
                                    // Log.d(TAG, "Document ID: " + document.getId() + " => " + document.getData());
                                    t_producto.append("Document ID: ").append(document.getId()).append("\n");
                                    t_producto.append("Document Data: ").append(document.getData())
                                            .append("\n\n");
                                }
                            } else {
                                Log.d(TAG, "No documents found.");
                            }
                        } else {
                            Log.e(TAG, "Error getting documents: ", task.getException());
                        }

                        txtTipoProducto.setText(t_producto.toString());

                    });

        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
    }
}