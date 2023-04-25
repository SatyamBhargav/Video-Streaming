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

public class MainActivity extends AppCompatActivity {

    EditText etemail;
    EditText etpass;
    TextView tvregister;
    Button btnlogin;
    ProgressBar pgloading;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvregister=findViewById(R.id.tvregister);
        etemail=findViewById(R.id.etemail);
        etpass=findViewById(R.id.etpass);
        btnlogin=findViewById(R.id.btnlogin);
        pgloading=findViewById(R.id.pgloading);
        mAuth=FirebaseAuth.getInstance();


        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnlogin.setVisibility(View.INVISIBLE);
                pgloading.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(etemail.getText().toString(),etpass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            etemail.setText("");
                            etpass.setText("");
                            btnlogin.setVisibility(View.VISIBLE);
                            pgloading.setVisibility(View.INVISIBLE);
                            Intent it=new Intent(getApplicationContext(),Dashboard.class);
                            startActivity(it);
                            Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            etemail.setText("");
                            etpass.setText("");
                            btnlogin.setVisibility(View.VISIBLE);
                            pgloading.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "Wrong Email Id or Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}