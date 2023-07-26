package com.vsga.app.finalprojectvsga.restapi.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class EmployeModel implements Parcelable {
    public static final Creator<EmployeModel> CREATOR = new Creator<EmployeModel>() {
        @Override
        public EmployeModel createFromParcel(Parcel in) {
            return new EmployeModel(in);
        }

        @Override
        public EmployeModel[] newArray(int size) {
            return new EmployeModel[size];
        }
    };
    private final String id;
    private final String name;
    private final String position;
    private final int salary;

    public EmployeModel(String id, String name, String position, int salary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    protected EmployeModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        position = in.readString();
        salary = in.readInt();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(position);
        parcel.writeInt(salary);
    }

    @NonNull
    @Override
    public String toString() {
        return "EmployeModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}
