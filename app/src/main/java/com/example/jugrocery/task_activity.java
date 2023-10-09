package com.example.jugrocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.model.DatabaseId;

public class task_activity extends AppCompatActivity implements View.OnClickListener{
    private Button btnsi,btnsu,btnfp;
    private EditText email,password;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private DatabaseId reference;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        firebaseAuth = FirebaseAuth.getInstance();

        btnsi=(Button) findViewById(R.id.signinid);
        btnsu=(Button) findViewById(R.id.signupid);
        btnfp=(Button) findViewById(R.id.forgetpasswordid);

        email=(EditText) findViewById(R.id.userid);
        password=(EditText) findViewById(R.id.passwordid);

        btnsi.setOnClickListener(this);
        btnsu.setOnClickListener(this);
//        btnfp.setOnClickListener(this);
        btnfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent intent=new Intent(task_activity.this, Login.class);
                startActivity(intent);
                finish();
                Toast.makeText(task_activity.this,"Logout successful",Toast.LENGTH_SHORT).show();
            }
        });

    }



    public void onClick(View view) {
        if(view.getId()==R.id.signinid)
        {

            login();



        }
        else if(view.getId()==R.id.signupid)
        {
            Intent intent = new Intent(task_activity.this, Register.class);
            startActivity(intent);

        }


    }

    private void login()
    {

        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        if(userEmail.isEmpty())
        {
            email.setError("Enter an email address");
            email.requestFocus();
            return;

        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches())
        {
            email.setError("Enter a valid email address");
            email.requestFocus();
            return;
        }

        if(userPassword.length()<8)
        {

            password.setError("Password is at least of 8 characters ");
            password.requestFocus();
            return;

        }

        firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign-in successful

                        Intent intent = new Intent(task_activity.this, StudentRegister.class);
                        startActivity(intent);
                        Toast.makeText(this, "Sign-in successful!", Toast.LENGTH_SHORT).show();


                    } else {
                        // Sign-in failed
                        email.setError( "Invalid email or password.");
                    }
                });
    }
//    private void logout(View view){
//        firebaseAuth.signOut();
//    }


}