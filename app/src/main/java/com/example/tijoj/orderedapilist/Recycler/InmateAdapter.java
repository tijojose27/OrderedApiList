package com.example.tijoj.orderedapilist.Recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tijoj.orderedapilist.POJO.InmatePOJO;
import com.example.tijoj.orderedapilist.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijoj on 5/23/2018.
 */

public class InmateAdapter extends RecyclerView.Adapter<InmateAdapter.InmateViewHolder> {


    public ArrayList<InmatePOJO> inmatePOJOList;

    public InmateAdapter(ArrayList<InmatePOJO> inmatePOJOList){
        this.inmatePOJOList = inmatePOJOList;
    }

    @NonNull
    @Override
    public InmateAdapter.InmateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_item, parent,false);

        return new InmateViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull InmateAdapter.InmateViewHolder holder, int position) {

        InmatePOJO inmatePOJO = inmatePOJOList.get(position);

        holder.facilityTV.setText("Facility ID : "+inmatePOJO.getFacility_id());
        holder.inmateIDTV.setText("ID : "+inmatePOJO.getInmate_id());
        holder.nameTV.setText(inmatePOJO.getLast_name()+", "+inmatePOJO.getFirst_name());

    }

    @Override
    public int getItemCount() {
        return inmatePOJOList==null ? 0 : inmatePOJOList.size();
    }

    public class InmateViewHolder extends RecyclerView.ViewHolder {
        public TextView facilityTV;
        public TextView inmateIDTV;
        public TextView nameTV;
        public InmateViewHolder(View itemView) {
            super(itemView);
            facilityTV = itemView.findViewById(R.id.facility_id_text_view_list);
            inmateIDTV = itemView.findViewById(R.id.inmate_id_text_view_list);
            nameTV = itemView.findViewById(R.id.name_text_view_list);
        }
    }
}


