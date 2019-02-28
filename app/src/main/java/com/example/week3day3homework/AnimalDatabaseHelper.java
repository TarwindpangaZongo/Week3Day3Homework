package com.example.week3day3homework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Locale;

import static com.example.week3day3homework.AnimalDatabaseContract.COLUMN_ID;
import static com.example.week3day3homework.AnimalDatabaseContract.COLUMN_NAME;
import static com.example.week3day3homework.AnimalDatabaseContract.COLUMN_SOUND;
import static com.example.week3day3homework.AnimalDatabaseContract.COLUMN_TYPE;
import static com.example.week3day3homework.AnimalDatabaseContract.DATABASE_NAME;
import static com.example.week3day3homework.AnimalDatabaseContract.DATABASE_VERSION;
import static com.example.week3day3homework.AnimalDatabaseContract.TABLE_NAME;
import static com.example.week3day3homework.AnimalDatabaseContract.getWhereClauseById;

public class AnimalDatabaseHelper extends SQLiteOpenHelper {
    public AnimalDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private void initialAnimals(){
        insertAnimalIntoDatabase(new Animal("human","Felix","talk"));
        insertAnimalIntoDatabase(new Animal("human","tom","talk"));
        insertAnimalIntoDatabase(new Animal("lion","king","Roar"));
        insertAnimalIntoDatabase(new Animal("Mammal","Amur Leopard","roar"));
        insertAnimalIntoDatabase(new Animal("Mammal","Goat","bleat"));
        insertAnimalIntoDatabase(new Animal("reptile","Turtle","bleat"));
        insertAnimalIntoDatabase(new Animal("Mammal","Goatr","bleat"));
        insertAnimalIntoDatabase(new Animal("Mammal","Goatr","bleat"));
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(AnimalDatabaseContract.createQuery());
        initialAnimals();

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        onCreate(database);
    }

    //Insert a Animal into the database
    public long insertAnimalIntoDatabase(@NonNull Animal animal) {
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        //Data container used for database key value pairs
        ContentValues contentValues = new ContentValues();

        //inset key value pairs into the contentValues container
        contentValues.put(COLUMN_TYPE, animal.getType());
        contentValues.put(COLUMN_NAME, animal.getName());
        contentValues.put(COLUMN_SOUND, animal.getSound());

        //insert the Animal into the table using contentValues
        return writableDatabase.insert(TABLE_NAME, null, contentValues);
    }

    //Get All Animals from Database and return an ArrayList
    public ArrayList<Animal> getAllAnimalsFromDatabase() {
        ArrayList<Animal> returnAnimalList = new ArrayList<>();
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        //Get results from query and hold in cursor(iterable object for database operations
        Cursor cursor = readableDatabase.rawQuery(AnimalDatabaseContract.getAllAnimalsQuery(), null);
        //Check to see if we have any results
        if(cursor.moveToFirst()) {
            //while we have results, get the values and place in list
            do {
                //get values
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String sound = cursor.getString(cursor.getColumnIndex(COLUMN_SOUND));



                //add to list
                returnAnimalList.add(new Animal(type, name, sound,id));
            } while (cursor.moveToNext());
            //return the result in a list
        }
        cursor.close();
        return returnAnimalList;
    }

    //Get One entry from database
    public Animal getAnimalById(int id) {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        //Animal to return
        Animal returnAnimal = new Animal();
        //cursor to hold results
        Cursor cursor = readableDatabase.rawQuery(AnimalDatabaseContract.getOneAnimalById(id), null);
        if(cursor.moveToFirst()){
            int idFromDB = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String sound = cursor.getString(cursor.getColumnIndex(COLUMN_SOUND));

            //set return Animal
            returnAnimal = new Animal(type, name, sound, idFromDB);
        }
        cursor.close();
        return returnAnimal;
    }

    //delete entry(ies) from the database
    public  long deleteFromDatabaseById(String[] id){
        SQLiteDatabase sqliteDatabas = this.getWritableDatabase();

        return sqliteDatabas.delete(TABLE_NAME,getWhereClauseById()+id[0], null);
    }
}
