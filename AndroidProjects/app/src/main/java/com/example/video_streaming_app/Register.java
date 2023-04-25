package com.example.video_streaming_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText etemail;
    EditText etpass;
    TextView tvsignin;
    Button btnregister;
    ProgressBar pgloading;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etemail=findViewById(R.id.etemail);
        etpass=findViewById(R.id.etpass);
        tvsignin=findViewById(R.id.tvsignin);
        btnregister=findViewById(R.id.btnregister);
        pgloading=findViewById(R.id.pgloading);
        mAuth=FirebaseAuth.getInstance();

        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(it);
            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnregister.setVisibility(View.INVISIBLE);
                pgloading.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(etemail.getText().toString(),etpass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            etemail.setText("");
                            etpass.setText("");
                            btnregister.setVisibility(View.VISIBLE);
                            pgloading.setVisibility(View.INVISIBLE);
                            Intent it=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(it);
                            Toast.makeText(Register.this, "User Registered", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            etemail.setText("");
                            etpass.setText("");
                            btnregister.setVisibility(View.VISIBLE);
                            pgloading.setVisibility(View.INVISIBLE);
                            Toast.makeText(Register.this, "User Not Registered", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}