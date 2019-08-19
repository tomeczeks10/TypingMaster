package com.szrl.tomasz.typingmaster;

import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StartTestActivity extends AppCompatActivity {

    private TextView loadData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starttest);
        loadData = (TextView) findViewById(R.id.input_textview);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReferenceFromUrl("https://keyboard-f2b83.firebaseio.com/Words");

// Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String w = ds.child("word").getValue(String.class);

                    // Words w = dataSnapshot.getValue(Words.class);
                    System.out.println("tomek" + w);
                    //  Log.i("msg",w.getWord());
                    loadData.setText(w);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }

}
