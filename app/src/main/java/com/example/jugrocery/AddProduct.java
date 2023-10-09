package com.example.jugrocery;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class AddProduct extends AppCompatActivity {
    private EditText Name, Price, Desc;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";
    private static final String KEY_DESCRIPTION = "description";
    /*Uri selectedImageUri;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Get the selected image's URI
            selectedImageUri = data.getData();
        }
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Name = findViewById(R.id.productName);
        Price = findViewById(R.id.productPrice);
        Desc = findViewById(R.id.productDescription);
    }

    public void saveProduct(View v){
        String name = Name.getText().toString();
        String price = Price.getText().toString();
        String description = Desc.getText().toString();

        Map<String, Object> product = new HashMap<>();
        product.put(KEY_NAME, name);
        product.put(KEY_PRICE, price);
        product.put(KEY_DESCRIPTION, description);


        db.collection("Product").add(product)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference aDocumentReference) {
                        // Successfully added the product, you can also add the image URI here
                      /*  if (selectedImageUri != null) {
                            // Save the image URI in Firestore or your chosen storage solution
                            String imageURL = selectedImageUri.toString();
                            documentReference.update("imageURL", imageURL);
                        }  */
                        Toast.makeText(AddProduct.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddProduct.this, "Something Wrong!!!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

}



