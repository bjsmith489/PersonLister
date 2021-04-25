package com.example.personlisterfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.MapFragment;

public class PersonDisplayScreen extends AppCompatActivity implements View.OnClickListener{

    Button displayUsersReturnButton;
    Button displayMapSwitchButton;
    boolean isDescriptionFragmentDisplayed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_display_screen);

        displayMapSwitchButton = findViewById(R.id.display_map_switch_button);
        displayUsersReturnButton = findViewById(R.id.return_button);

        displayUsersReturnButton.setOnClickListener(this);
        displayMapSwitchButton.setOnClickListener(this);

        displayDescriptionFragment();
    }

    public void displayDescriptionFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PersonDisplayDescriptionFragment pddf = PersonDisplayDescriptionFragment.newInstance();
        Bundle bundle = setUserInfo();
        pddf.setArguments(bundle);
        ft.add(R.id.person_description_fragment, pddf);
        ft.commit();
    }

    public void displayMapFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PersonDisplayMapFragment pdmf = new PersonDisplayMapFragment();
        Bundle bundle = setUserInfo();
        pdmf.setArguments(bundle);
        ft.add(R.id.person_description_fragment, pdmf);
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
    }
    public void closeMapFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PersonDisplayMapFragment mapFragment = (PersonDisplayMapFragment) fm
                .findFragmentById(R.id.person_description_fragment);
        if (mapFragment != null) {
            // Create and commit the transaction to remove the fragment.
            FragmentTransaction fragmentTransaction =
                    fm.beginTransaction();
            fragmentTransaction.remove(mapFragment).commit();
        }
    }
    private Bundle setUserInfo() {
        Bundle bundle = new Bundle();
        User user = getIntent().getParcelableExtra("user");
        if(user != null) {
            bundle.putString("name",user.getName());
            bundle.putString("username", user.getUserName());
            bundle.putString("email",user.getEmail());
            bundle.putDouble("lat", user.getAddress().getGeo().getLat());
            bundle.putDouble("lng", user.getAddress().getGeo().getLng());
        }
        return bundle;
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.return_button:
                returnToDisplayScreen();
                break;
            case R.id.display_map_switch_button:
                if(isDescriptionFragmentDisplayed){
                    displayMapSwitchButton.setText("Display Info");
                    isDescriptionFragmentDisplayed = false;
                    closeDescriptionFragment();
                    displayMapFragment();
                }
                else{
                    isDescriptionFragmentDisplayed = true;
                    displayMapSwitchButton.setText("Display Map");
                    closeMapFragment();
                    displayDescriptionFragment();
                }
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