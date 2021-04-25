package com.example.personlisterfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class PersonDisplayMapFragment extends Fragment {
    TextView nameTextView;
    TextView usernameTextView;
    TextView emailTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_person_description, container, false);
        nameTextView = view.findViewById(R.id.textView4);
        usernameTextView = view.findViewById(R.id.textView3);
        emailTextView = view.findViewById(R.id.textView2);
        return inflater.inflate(R.layout.fragment_person_map, container, false);
    }

}
