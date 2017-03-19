package com.willyao.android.timepickeralarm.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by mitya on 3/18/2017.
 */

public class Todo implements Parcelable {

    public Date remindDate;

    public  Todo(Date remindDate) {
        this.remindDate = remindDate;
    }

    protected Todo(Parcel in) {
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(remindDate != null ? remindDate.getTime() : 0);
    }
}
