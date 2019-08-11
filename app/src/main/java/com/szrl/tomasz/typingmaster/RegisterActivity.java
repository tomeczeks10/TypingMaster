package com.szrl.tomasz.typingmaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private Button register_but;
    private EditText login_reg, password_reg;
    private TextView clicktolog;
    private String login, password;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_but = (Button) findViewById(R.id.register_butt);
        login_reg = (EditText) findViewById(R.id.email_text2);
        password_reg = (EditText) findViewById(R.id.password_text2);
        clicktolog = (TextView) findViewById(R.id.click_to_reg);

        clicktolog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(RegisterActivity.this, MenuActivity.class));
                    finish();
                } else if (user == null) {
                    //   Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_LONG).show();
                }
            }
        };

        register_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = login_reg.getText().toString();
                password = password_reg.getText().toString();

                if (!login.equals("") && !password.equals("") && password.length() >= 6) {
                    mAuth.createUserWithEmailAndPassword(login, password);
                    Toast.makeText(RegisterActivity.this, "Success!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this, MenuActivity.class));
                } else {
                    Toast.makeText(RegisterActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}


