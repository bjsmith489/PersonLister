package com.example.personlisterfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class PersonDisplayDescriptionFragment extends Fragment {
    TextView nameTextView;
    TextView usernameTextView;
    TextView emailTextView;

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

        String name = bundle.getString("name");
        String username = bundle.getString("username");
        String email = bundle.getString("email");

        nameTextView.setText(name);
        usernameTextView.setText(username);
        emailTextView.setText(email);

        return view;
    }

}
