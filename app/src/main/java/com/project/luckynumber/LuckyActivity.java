package com.project.luckynumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LuckyActivity extends AppCompatActivity {

    TextView luckyNumberTextView;
    Button shareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky);

        luckyNumberTextView = findViewById(R.id.luckyNumberTextView);
        shareBtn = findViewById(R.id.shareBtn);

        Intent i = getIntent();
        String userName = i.getStringExtra("name");
        Toast.makeText(this, "User Name: " + userName, Toast.LENGTH_LONG).show();

        int randomNumber = generateRandomNumber();
        luckyNumberTextView.setText(randomNumber + "");

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData(userName, randomNumber);
            }
        });
    }

    private int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(1000);
    }

    private void shareData(String userName, int randomNumber) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        i.putExtra(Intent.EXTRA_SUBJECT, userName + " got lucky today");
        i.putExtra(Intent.EXTRA_TEXT, "Your lucky number is: " + randomNumber);

        startActivity(Intent.createChooser(i, "Choose a platform"));
    }
}