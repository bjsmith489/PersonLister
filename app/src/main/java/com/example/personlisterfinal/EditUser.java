package com.example.personlisterfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class EditUser extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button returnButton;
    EditText nameEditText;
    ImageView pictureImageView;
    Spinner dropdown;
    Integer[] pictures = new Integer[]{R.drawable.knight_avatar, R.drawable.ninja_avatar, R.drawable.rainbow_dash_avatar};
    String[] names = new String[]{"Knight", "Ninja", "Rainbow Dash"};
    int choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        returnButton = findViewById(R.id.save_and_return);
        nameEditText = findViewById(R.id.edit_name);
        pictureImageView = findViewById(R.id.user_image);

        returnButton.setOnClickListener(this);

        //get the spinner from the xml.
        dropdown = findViewById(R.id.picture_spinner);
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, names);
        //set the spinners adapter to the previously created one.
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.save_and_return:
                saveAndReturn();
                break;
        }
    }

    private void saveAndReturn() {
        SharedPreferences sharedPreferences = getSharedPreferences("userSharedPreference", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(!"".equals(nameEditText.getText().toString())){
            editor.putString("name", nameEditText.getText().toString());
        }
        if(choice == 1){
            Log.v("TAG:  ",   "---------------------------");
            editor.putInt("picture", pictures[dropdown.getSelectedItemPosition()]);
        }
        editor.commit();
        startActivity(new Intent(this, ListUsers.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(position){
            case 0:
                pictureImageView.setImageResource(pictures[0]);
                break;
            case 1:
                pictureImageView.setImageResource(pictures[1]);
                break;
            case 2:
                pictureImageView.setImageResource(pictures[2]);
                break;
        }
        choice = 1;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        choice = 0;
    }
}