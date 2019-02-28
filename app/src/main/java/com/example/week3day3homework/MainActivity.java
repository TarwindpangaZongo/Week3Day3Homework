package com.example.week3day3homework;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyRecyclerVewAdapter myRecyclerViewAdapter;
    AnimalDatabaseHelper animalDatabaseHelper;

    //declare views
    EditText etType;
    EditText etName;
    EditText etSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind other views used to insert Beverage
        etType = findViewById(R.id.etType);
        etName = findViewById(R.id.etName);
        etSound = findViewById(R.id.etSound);

        //Bind RecyclerView
        recyclerView = findViewById(R.id.rvRecyclerView);

        //Recycler View needs 2 items
        //  1. Layout Manager (Can be customized, but we generally us a default
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //  2.RecyclerView adapter (We write this)
        ArrayList<Animal> animalLsist = animalDatabaseHelper.getAllAnimalsFromDatabase();
        myRecyclerViewAdapter = new MyRecyclerVewAdapter(animalLsist);
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }

    public void saveAnimalToDBandSeeLog(@NonNull Animal animal){
        //Save animal to database
        animalDatabaseHelper.insertAnimalIntoDatabase(animal);
        //get all current animals in database and log them
        ArrayList<Animal> animalList = animalDatabaseHelper.getAllAnimalsFromDatabase();
        for(Animal currentAnimal : animalList) {
            Log.d("TAG", currentAnimal.toString());
        }
    }

    public void onClick(View view) {
        //Get input from user
        String type = etType.getText().toString();
        String name = etName.getText().toString();
        String sound = etSound.getText().toString();
        //Make an object that matches the object passed in the ArrayList
        Animal animalToInsert = new Animal(type,name,sound);
        saveAnimalToDBandSeeLog(animalToInsert);

        //Call the method in the adapter to add the beverage to list
        myRecyclerViewAdapter.addAnimalToList(animalToInsert);
    }




}
