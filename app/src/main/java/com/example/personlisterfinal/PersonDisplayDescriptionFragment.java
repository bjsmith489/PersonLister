package com.example.personlisterfinal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.io.File;

public class PersonDisplayDescriptionFragment extends Fragment implements View.OnClickListener{
    TextView nameTextView;
    TextView usernameTextView;
    TextView emailTextView;
    ImageView imageView;
    public PersonDisplayDescriptionFragment() {
    }

    public static PersonDisplayDescriptionFragment newInstance() {
        return new PersonDisplayDescriptionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_person_description, container, false);
        Bundle bundle = getArguments();
        nameTextView = view.findViewById(R.id.textView4);
        usernameTextView = view.findViewById(R.id.textView3);
        emailTextView = view.findViewById(R.id.textView2);
        imageView = view.findViewById(R.id.imageView);

        String imageFilePath = bundle.getString("imageFilePath");
        String name = bundle.getString("name");
        String username = bundle.getString("username");
        String email = bundle.getString("email");
        System.out.println(imageFilePath);
        File imageFile = new File(imageFilePath);
        nameTextView.setText(name);
        usernameTextView.setText(username);
        emailTextView.setText(email);
        Picasso.get().load(imageFile).into(imageView);

        imageView.setOnClickListener(this);
        
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case(R.id.imageView):
                showDialog();
                break;
        }
    }

    void showDialog() {
        DialogFragment newFragment = CameraPermissionDialogFragment.newInstance(
                R.string.confirm_camera);
        newFragment.show(getFragmentManager(), "dialog");
    }
}
