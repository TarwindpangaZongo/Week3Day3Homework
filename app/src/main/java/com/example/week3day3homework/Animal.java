package com.example.week3day3homework;

import android.os.Parcel;
import android.os.Parcelable;

public class Animal implements Parcelable {
    private String Type;
    private String name;
    private String Sound;
    private int animalId;


    protected Animal(Parcel in) {
        Type = in.readString();
        name = in.readString();
        Sound = in.readString();
        animalId = in.readInt();
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

    public Animal() {
    }

    public Animal(String type, String name, String sound, int animalId) {
        Type = type;
        this.name = name;
        Sound = sound;
        this.animalId = animalId;
    }

    public Animal(String type, String name, String sound) {
        Type = type;
        this.name = name;
        Sound = sound;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Type);
        dest.writeString(name);
        dest.writeString(Sound);
        dest.writeInt(animalId);
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSound() {
        return Sound;
    }

    public void setSound(String sound) {
        Sound = sound;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "Type='" + Type + '\'' +
                ", name='" + name + '\'' +
                ", Sound='" + Sound + '\'' +
                ", animalId=" + animalId +
                '}';
    }
}
