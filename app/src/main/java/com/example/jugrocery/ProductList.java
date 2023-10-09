package com.example.jugrocery;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;


public class ProductList extends AppCompatActivity {


    private static final String KEY_TITLE = "title";
    private static final String KEY_PRICE = "price";
    private static final String KEY_DESCRIPTION = "description";

    private TextView textViewData;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference product = db.collection("Product");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        textViewData = findViewById(R.id.text_view_data);
    }


    public void loadNotes(View v) {
        product.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    String data = "";

                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        try {
                            Product note = documentSnapshot.toObject(Product.class);
                            note.setId(documentSnapshot.getId());

                            String documentId = note.getId();
                            String title = note.getName();
                            String price = note.getPrice();
                            String description = note.getDescription();

                            data += "ID: " + documentId
                                    + "\nName: " + title + "\nPrice: " + price + "\nDescription: " + description + "\n\n";
                        } catch (Exception e) {
                            // Handle the exception, e.g., log it or display an error message
                            e.printStackTrace();
                        }
                    }

                    textViewData.setText(data);
                })
                .addOnFailureListener(e -> {
                    // Handle Firestore query failure here
                    e.printStackTrace();
                });
    }

}

