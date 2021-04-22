package com.example.personlisterfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListUsers extends AppCompatActivity implements View.OnClickListener {
    private Gson gson = new Gson();
    private Button logoutButton;
    private GoogleSignInClient mGoogleSignInClient;
    User[] users;
    TextView userTextView;
    ImageView userImageView;
    CardView personCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        setUsers();

        //getting the reference of RecyclerView
        logoutButton = findViewById(R.id.logout);
        personCardView = findViewById(R.id.cardView);

        logoutButton.setOnClickListener(this);
        personCardView.setOnClickListener(this);

    }

    public void onResume() {
        super.onResume();
        setUserInformation();
    }
    public void setUsers() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(getString(R.string.url)).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String myResponse = response.body().string();
                    ListUsers.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            users = gson.fromJson(myResponse, User[].class);
                            displayUsers(users);
                        }
                    });
                }
                Log.v("TAG", "Verbose level message");
            }
        });
    }

    public void displayUsers(User[] users){
        if(users != null) {
            ListUsers.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setRecyclerView();
                }
            });
        }
    }
    public void setRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);   // Setting the orientation
        UserAdapter customAdapter = new UserAdapter(this, users);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.logout:
                signOut();
                break;
            case R.id.cardView:
                userEdit();
                break;
        }
    }

    private void userEdit() {
        startActivity(new Intent(this, EditUser.class));
    }

    private void signOut() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }

    private void updateUI(Object o) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        Toast.makeText(this, "Signed out Success", Toast.LENGTH_LONG).show();
    }

    public void setUserInformation(){
        SharedPreferences sharedPreferences = getSharedPreferences("userSharedPreference", MODE_PRIVATE);
        userImageView = findViewById(R.id.user_image);
        userTextView = findViewById(R.id.user_name);

        if(sharedPreferences.getString("name", "").equals("")){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {

                //Todo: set the image to the google picture
                String name = acct.getDisplayName();
                Uri picture = acct.getPhotoUrl();

                userTextView.setText(name);
                userImageView.setImageURI(picture);

                //Todo: Save the image shared preference
                editor.putString("name", name);
               // editor.putString("picture", picture.toString());
                editor.commit();
            }
        }
        else{
           String name = sharedPreferences.getString("name", "");
           int picture = sharedPreferences.getInt("picture", R.drawable.knight_avatar);
           Log.v("TAG:  ",   "---------------------------");
           userTextView.setText(name);
           userImageView.setImageResource(picture);
        }
    }
}