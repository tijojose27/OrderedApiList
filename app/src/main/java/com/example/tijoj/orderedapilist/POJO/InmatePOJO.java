package com.example.tijoj.orderedapilist.POJO;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by tijoj on 5/23/2018.
 */

public class InmatePOJO implements Parcelable{

    public int facility_id;
    public String first_name;
    public String last_name;
    public int inmate_id;

    public InmatePOJO(int facility_id, String first_name, String last_name, int inmate_id) {
        this.facility_id = facility_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.inmate_id = inmate_id;
    }

    protected InmatePOJO(Parcel in) {
        facility_id = in.readInt();
        first_name = in.readString();
        last_name = in.readString();
        inmate_id = in.readInt();
    }

    public static final Creator<InmatePOJO> CREATOR = new Creator<InmatePOJO>() {
        @Override
        public InmatePOJO createFromParcel(Parcel in) {
            return new InmatePOJO(in);
        }

        @Override
        public InmatePOJO[] newArray(int size) {
            return new InmatePOJO[size];
        }
    };

    public int getFacility_id() {
        return facility_id;
    }

    public void setFacility_id(int facility_id) {
        this.facility_id = facility_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getInmate_id() {
        return inmate_id;
    }

    public void setInmate_id(int inmate_id) {
        this.inmate_id = inmate_id;
    }

    public static Comparator<InmatePOJO> byInmateId = new Comparator<InmatePOJO>() {
        @Override
        public int compare(InmatePOJO o1, InmatePOJO o2) {
            int inmate1 = o1.getInmate_id();
            int inmate2= o2.getInmate_id();
            return inmate1-inmate2;
        }
    };

    public static Comparator<InmatePOJO> byInmateName = new Comparator<InmatePOJO>() {
        @Override
        public int compare(InmatePOJO o1, InmatePOJO o2) {
            String inmate1 = o1.getLast_name();
            String inmate2 = o2.getLast_name();

            return inmate1.compareTo(inmate2);
        }
    };

    public static Comparator<InmatePOJO> byFacility = new Comparator<InmatePOJO>() {
        @Override
        public int compare(InmatePOJO o1, InmatePOJO o2) {
            int facility1 = o1.getFacility_id();
            int facility2= o2.getFacility_id();
            return facility1-facility2;
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(facility_id);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeInt(inmate_id);
    }
}
