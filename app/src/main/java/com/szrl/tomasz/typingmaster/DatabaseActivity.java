package com.szrl.tomasz.typingmaster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button submit;
    private TextInputLayout in;
    String words;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference();
        submit = (Button)findViewById(R.id.button4);
        in = (TextInputLayout)findViewById(R.id.textInputLayout);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                words = in.getEditText().getText().toString().trim();
                if(!words.isEmpty()) {
                    Words w = new Words(words);
                    ref.child("Words").push().setValue(w);
                    in.getEditText().setText("");
                    Toast.makeText(DatabaseActivity.this, "Data added!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(DatabaseActivity.this, "Input is empty!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
