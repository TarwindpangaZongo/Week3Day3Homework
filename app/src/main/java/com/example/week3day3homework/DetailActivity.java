package com.example.week3day3homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    public static ArrayList<String> savedList = new ArrayList<>();
    public static String[] holding;
    RecyclerView recyclerView;
    AnimalDatabaseHelper animalDatabaseHelper;
    MyRecyclerVewAdapter myRecyclerViewAdapter;

    Intent passedIntent;
    Animal animal;
    ListView lstPassedAnimals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        lstPassedAnimals = findViewById(R.id.lstSimpleListView);

        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, savedList);

        if(getIntent() != null) {
            passedIntent = getIntent();
            Bundle bundle = passedIntent.getExtras();

            animal = bundle.getParcelable("bev");
            savedList.add(animal.getName());
            Toast.makeText(this, animal.getName() + " was passed" , Toast.LENGTH_LONG).show();
        }

        lstPassedAnimals.setAdapter(arrayAdapter);
    }

    public void onClick(View view) {
        animalDatabaseHelper.deleteFromDatabaseById(holding);
        //Call the method in the adapter to add the beverage to list

        recyclerView.setAdapter(myRecyclerViewAdapter);;
    }
}
