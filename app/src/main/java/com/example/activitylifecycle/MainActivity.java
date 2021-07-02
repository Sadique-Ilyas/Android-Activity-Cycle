package com.example.activitylifecycle;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    TextView textView, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        textView = findViewById(R.id.text);
        textView2 = findViewById(R.id.text2);
        Toast.makeText(this, "onCreate Called !", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart Called !", Toast.LENGTH_SHORT).show();
        textView.setText("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume Called !", Toast.LENGTH_SHORT).show();
        firebaseDatabase.getReference("value")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Long value = snapshot.getValue(Long.class);
                        Calendar calendar = Calendar.getInstance();
                        String myTime = DateFormat.getTimeInstance(DateFormat.DATE_FIELD).format(calendar.getTime());
                        if (value == 1) {
                            textView.setText("start time: " + myTime);
                        } else {
                            textView2.setText("end time: " + myTime);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause Called !", Toast.LENGTH_SHORT).show();
        textView.setText("onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart Called !", Toast.LENGTH_SHORT).show();
        textView.setText("onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop Called !", Toast.LENGTH_SHORT).show();
        textView.setText("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy Called !", Toast.LENGTH_SHORT).show();
        textView.setText("onDestroy");
    }
}