package com.example.personlisterfinal;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    User[] userList;
    Context context;

    public UserAdapter(Context context, User[] users) {
        this.userList = users;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_person, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // set the data in items
        PicassoManager picassoManager = new PicassoManager(context.getApplicationContext(),
                "imageDir",
                "person_image_"+position);
        File imageFile = picassoManager.loadImageFile();
        String imageFilePath= imageFile.getPath();
        User user = userList[position];
        if(!imageFile.exists()){
            Picasso.get()
                    .load("https://robohash.org/"+position+"?set=set4")
                    .resize(500, 500)
                    .centerCrop()
                    .placeholder(R.drawable.temp_image).into(
                    picassoManager.picassoImageTarget());
        }
        Picasso.get().load(imageFile).into(holder.image);
        holder.name.setText(user.getName());
        user.setImage(R.drawable.temp_image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Directs to PersonDisplayScreen to display the holder's information
                Intent personDisplayIntent = new Intent(context, PersonDisplayScreen.class);
                personDisplayIntent.putExtra("imageFilePath", imageFilePath);
                personDisplayIntent.putExtra("user", user);
                context.startActivity(personDisplayIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.name);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
