package com.example.tijoj.orderedapilist;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.example.tijoj.orderedapilist.POJO.InmatePOJO;
import com.example.tijoj.orderedapilist.Recycler.InmateAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class ListActivity extends AppCompatActivity {

    public ArrayList<InmatePOJO> inmateDetails;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //SETTUG TOOLBAR
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //GETTING THE VIEWS
        coordinatorLayout = findViewById(R.id.coordinator);
        recyclerView = findViewById(R.id.recyclerView);
        //SETTING SIZE OF RECYCLERVIEW
        recyclerView.setHasFixedSize(true);

        //CHECKING FOR SAVED INSTANCE STATE AND GETTING DATA FROM THE MAIN ACITIVITY
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            inmateDetails = extras.getParcelableArrayList(getString(R.string.INMATE_DETAILS));
        }if(savedInstanceState!=null){
            inmateDetails = savedInstanceState.getParcelableArrayList(getString(R.string.INMATE_DETAILS));
        }
        //IF THERE IS NO DATA SHOW CUSTOM SCREEN
        if(inmateDetails ==null){
            setContentView(R.layout.no_list);
        }

        // UPDATING RECYCLERVIEW IF AVAIABLE
        updateRecyclerView(inmateDetails);

    }

    //UPDATE RECYCLER VIEW
    private void updateRecyclerView(ArrayList<InmatePOJO> inmateDetails) {
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new InmateAdapter(inmateDetails);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        runLayoutAnimation(recyclerView);
    }

    // ANIMATION ON RECYCLERVIEW
    private void runLayoutAnimation(RecyclerView recyclerView) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_drop_down);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }


    //GETTINT THE MENU ITEM
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //GETTING WHAT WAS CLICKED FROM THE TOOLBAR
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.sortByName:
                Collections.sort(inmateDetails, InmatePOJO.byInmateName);
                updateRecyclerView(inmateDetails);
                Snackbar.make(coordinatorLayout, "SORTED BY NAME", Snackbar.LENGTH_SHORT).show();
                return true;
            case R.id.sortByInmateId:
                Collections.sort(inmateDetails, InmatePOJO.byInmateId);
                Snackbar.make(coordinatorLayout, "SORTED BY ID", Snackbar.LENGTH_SHORT).show();
                updateRecyclerView(inmateDetails);
                return true;
            case R.id.sortByFacility:
                Collections.sort(inmateDetails, InmatePOJO.byFacility);
                Snackbar.make(coordinatorLayout, "SORTED BY FACILITY", Snackbar.LENGTH_SHORT).show();
                updateRecyclerView(inmateDetails);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //IF DEVICE IS ROTATED
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(getString(R.string.INMATE_DETAILS), inmateDetails);
    }
}