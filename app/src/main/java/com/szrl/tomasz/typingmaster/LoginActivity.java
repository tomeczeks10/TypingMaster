package com.szrl.tomasz.typingmaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private Button login_but;
    private EditText login_log,password_log;
    private TextView clicktoreg;
    private String login,password;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_but=(Button)findViewById(R.id.login_butt);
        login_log=(EditText)findViewById(R.id.email_text);
        password_log=(EditText)findViewById(R.id.password_text);
        clicktoreg=(TextView)findViewById(R.id.click_to_log);

        clicktoreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });

        mAuth=FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null)
                {
                    startActivity(new Intent(LoginActivity.this,MenuActivity.class));
                    finish();
                }
                else if (user == null)
                {
                 //   Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_LONG).show();
                }
            }
        };

        login_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login=login_log.getText().toString();
                password=password_log.getText().toString();

                if(!login.equals("") && !password.equals("") && password.length()>=6)
                {
                    mAuth.signInWithEmailAndPassword(login,password);
                    Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this,MenuActivity.class));
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                }
            }
        });


}


}
