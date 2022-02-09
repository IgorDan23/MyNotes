package com.example.mynotes;

import android.os.Parcel;
import android.os.Parcelable;

public class Notes implements Parcelable {
    protected Notes(Parcel in) {
        number = in.readInt();
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    public int getNumber() {
        return number;
    }

    public Notes(int number) {
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private int number;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(number);
    }
}
