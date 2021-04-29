package com.example.privatemessaging20;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DecryptAuth extends AppCompatActivity {

    TextInputEditText email,password;
    Button verify;

    FirebaseAuth auth2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt_auth);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Verification");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth2 = FirebaseAuth.getInstance();

        email = findViewById(R.id.email2);
        password = findViewById(R.id.password2);
        verify = findViewById(R.id.btn_verify);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(DecryptAuth.this,"All fields are required",Toast.LENGTH_SHORT).show();
                }else {
                    auth2.signInWithEmailAndPassword(txt_email,txt_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(DecryptAuth.this,Decoder.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(DecryptAuth.this,"Authentication failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}