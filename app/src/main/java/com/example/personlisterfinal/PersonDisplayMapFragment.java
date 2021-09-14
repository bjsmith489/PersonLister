package com.example.personlisterfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PersonDisplayMapFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        //Initialize view
        View view = inflater.inflate(R.layout.fragment_person_map, container, false);
        setGoogleMap();
        return view;
    }

    public void setGoogleMap(){
        // Initialize map fragment
        Bundle bundle = getArguments();
        Double lat = bundle.getDouble("lat");
        Double lng = bundle.getDouble("lng");

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map_frame_layout);
        // Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // When map is loaded
                // Mark with LatLng
                LatLng latLng = new LatLng(lat,lng);
                MarkerOptions personLocation = new MarkerOptions();
                personLocation.position(latLng);
                googleMap.addMarker(personLocation);
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        //when clicked on map
                        // Initialize marker option
                        MarkerOptions markerOptions = new MarkerOptions();
                        // set position of marker
                        markerOptions.position(latLng);
                        //Set title of marker
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                        //Remove all markers
                        googleMap.clear();
                        //Animatinf to zoom the marker
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom( latLng, 10));
                        //Add marker on map
                        googleMap.addMarker(markerOptions);
                    }
                });
            }
        });
    }

    // AIzaSyBcoAviXaiDukliXeh_Z5bqT7kO6T80QqQ
}
