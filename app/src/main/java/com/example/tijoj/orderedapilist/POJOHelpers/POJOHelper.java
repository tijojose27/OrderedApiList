package com.example.tijoj.orderedapilist.POJOHelpers;

import android.util.Log;

import com.example.tijoj.orderedapilist.POJO.InmatePOJO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by tijoj on 5/23/2018.
 */

public class POJOHelper {

    //CONVERT THE JSON TO POJO
    public static ArrayList<InmatePOJO> getInmateFromPojo(String json){
         ArrayList<InmatePOJO> inmatePOJOArrayList =new ArrayList<>();
        try {
            JSONArray obj= new JSONArray(json);
            for(int i=0; i<obj.length();i++){
                JSONObject object = obj.getJSONObject(i);
                String first_name = object.getString("first_name");
                String last_name = object.getString("last_name");
                int facility_id = object.getInt("facility_id");
                int inamte_id = object.getInt("inmate_id");
                inmatePOJOArrayList.add(new InmatePOJO(facility_id, first_name, last_name, inamte_id));
            }
        } catch (JSONException e) {
            Log.e("Wrong", e.toString());
        }
        return inmatePOJOArrayList;
    }
    //END

}
