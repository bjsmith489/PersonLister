package com.example.personlisterfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PersonDisplayScreen extends AppCompatActivity implements View.OnClickListener{

    Button displayUsersReturnButton;
    boolean isDescriptionFragmentDisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_display_screen);

        displayUsersReturnButton = findViewById(R.id.return_button);
        displayUsersReturnButton.setOnClickListener(this);

        displayDescriptionFragment();
        setUserInfo();
    }

    public void displayDescriptionFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PersonDisplayDescriptionFragment pddf = PersonDisplayDescriptionFragment.newInstance();
        Bundle bundle = setUserInfo();
        pddf.setArguments(bundle);
        ft.add(R.id.person_description_fragment, pddf);
        isDescriptionFragmentDisplayed = true;
        ft.commit();
    }

    public void closeDescriptionFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PersonDisplayDescriptionFragment descriptionFragment = (PersonDisplayDescriptionFragment) fm
                .findFragmentById(R.id.person_description_fragment);
        if (descriptionFragment != null) {
            // Create and commit the transaction to remove the fragment.
            FragmentTransaction fragmentTransaction =
                    fm.beginTransaction();
            fragmentTransaction.remove(descriptionFragment).commit();
        }
        isDescriptionFragmentDisplayed = false;
    }
    private Bundle setUserInfo() {
        Bundle bundle = new Bundle();
        User user = getIntent().getParcelableExtra("user");
        if(user != null) {
            bundle.putString("name",user.getName());
            bundle.putString("username", user.getUserName());
            bundle.putString("email",user.getEmail());
        }
        return bundle;
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.return_button:
                returnToDisplayScreen();
                break;
            case R.id.view_address_button:
                viewGoogleMap();
                break;
        }
    }

    private void viewGoogleMap() {

    }

    private void returnToDisplayScreen() {
        Intent returnIntent = new Intent(this, ListUsers.class);
        startActivity(returnIntent);
    }
}