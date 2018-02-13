package com.mercacortex.ad_entrega_t4.model;


import android.os.Parcel;
import android.os.Parcelable;

public class CityWeather implements Parcelable {
    public static final Creator<CityWeather> CREATOR = new Creator<CityWeather>() {
        @Override
        public CityWeather createFromParcel(Parcel in) {
            return new CityWeather(in);
        }

        @Override
        public CityWeather[] newArray(int size) {
            return new CityWeather[size];
        }
    };
    int id;
    String name;

    public CityWeather() {
    }

    public CityWeather(int id, String name) {
        this.id = id;
        this.name = name;
    }

    protected CityWeather(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
