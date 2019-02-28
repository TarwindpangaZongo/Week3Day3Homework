package com.example.week3day3homework;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Random;

public class MyRecyclerVewAdapter extends RecyclerView.Adapter<MyRecyclerVewAdapter.ViewHolder> {
    //List of animal that will be populated into the recycler view
    ArrayList<Animal> animalsArrayList;

    //Constructor for the Adapter
    public MyRecyclerVewAdapter(ArrayList<Animal> animalsArrayList) {
        this.animalsArrayList = animalsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.animal_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerVewAdapter.ViewHolder viewHolder, final int position) {
        //Get the item's information which we wish to populate for that viewholder
        Animal currentAnimalBeingPopulated = animalsArrayList.get(position);
        //use the passed viewholder to access the items view and populate
        viewHolder.tvType.setText(currentAnimalBeingPopulated.getType());
        viewHolder.tvname.setText(currentAnimalBeingPopulated.getName());
        viewHolder.tvSound.setText(currentAnimalBeingPopulated.getSound());
        viewHolder.tvPopulation.setText(population());
        //viewHolder.ivImage.setText(currentAnimalBeingPopulated.getImageUrl());
        Log.d("TAG", "onBindViewHolder: item being rendered = " + position);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), animalsArrayList.get(position).getName() + "clicked", Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putParcelable("animal", animalsArrayList.get(position));
                Intent intentToStartDetails = new Intent(v.getContext(), DetailActivity.class);
                intentToStartDetails.putExtras(bundle);
                v.getContext().startActivity(intentToStartDetails);
            }
        });
    }
    private String population(){
        Random rand = new Random();
        int population = 100000;
        return Integer.toString(rand.nextInt(population));
    }

    //Add to list, notify the adapter that the info in the list has changed
    public void addAnimalToList(Animal animal) {
        animalsArrayList.add(animal);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return animalsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvType;
        TextView tvname;
        TextView tvSound;
        TextView tvPopulation;
        ImageView ivImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.tvType);
            tvname = itemView.findViewById(R.id.tvName);
            tvSound = itemView.findViewById(R.id.tvSound);
            tvPopulation = itemView.findViewById(R.id.tvPopulation);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
