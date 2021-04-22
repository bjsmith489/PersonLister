package com.example.personlisterfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PersonDisplayScreen extends AppCompatActivity implements View.OnClickListener{

    Button displayUsersReturnButton;
    TextView nameTextView;
    TextView usernameTextView;
    TextView emailTextView;
    TextView addressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_display_screen);

        displayUsersReturnButton = findViewById(R.id.return_button);
        nameTextView = findViewById(R.id.textView4);
        usernameTextView = findViewById(R.id.textView3);
        emailTextView = findViewById(R.id.textView2);
        addressTextView = findViewById(R.id.textView5);

        displayUsersReturnButton.setOnClickListener(this);
        setUserInfo();
    }

    private void setUserInfo() {
        User user = getIntent().getParcelableExtra("user");
        if(user != null) {
            nameTextView.setText(user.getName());
            usernameTextView.setText(user.getUserName());
            emailTextView.setText(user.getEmail());
            addressTextView.setText((user.getAddress().toString()));
        }
        Toast.makeText(this, user.toString(), Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.return_button:
                returnToDisplayScreen();
                break;
        }
    }

    private void returnToDisplayScreen() {
        Intent returnIntent = new Intent(this, ListUsers.class);
        startActivity(returnIntent);
    }
}