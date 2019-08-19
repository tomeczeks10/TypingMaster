package com.szrl.tomasz.typingmaster;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.LinkedList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private Button logout_button;
    private Button database_button;
    private Button start_button;
    private EditText editText;
    private FirebaseAuth mAuth;

    private List<String> dictionary = new LinkedList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mAuth = FirebaseAuth.getInstance();
        logout_button = (Button) findViewById(R.id.button2);
        start_button = (Button) findViewById(R.id.button3);
        database_button = (Button) findViewById(R.id.data_but);
        //showSoftKeyboard(editText);
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this,StartTestActivity.class));
            }
        });
        database_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, DatabaseActivity.class));
            }
        });
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(MenuActivity.this, MainActivity.class));

            }
        });


    }

}