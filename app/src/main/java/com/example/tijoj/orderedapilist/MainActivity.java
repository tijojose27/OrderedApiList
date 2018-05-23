package com.example.tijoj.orderedapilist;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.tijoj.orderedapilist.POJO.InmatePOJO;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.tijoj.orderedapilist.POJOHelpers.POJOHelper.getInmateFromPojo;


public class MainActivity extends AppCompatActivity {

    TextView firstTV;
    TextView secondTV;
    TextView thirdTV;

    ArrayList<InmatePOJO> inmateDetailsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //GETTING ID
        firstTV = findViewById(R.id.first_intro_text_view_main);
        secondTV = findViewById(R.id.second_intro_text_view_main);
        thirdTV = findViewById(R.id.third_intro_text_view_main);

        //TODO REPLACE THE STRING VALUE WITH THE URL
        final String url=getString(R.string.URL);

        //SETTING THE OPCAITY TO MAKE THE TEXTVIEW INVISIBLES
        secondTV.setAlpha(0f);
        thirdTV.setAlpha(0f);

        //GETTING THE DATA FROM REST API ON BACKGROUND
        getInmateDetails(url);

        //STARTING FIRST ANIMATION - DISTRACTION
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                firstTV.animate()
                        .alpha(0f)
                        .setDuration(1000)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                firstTV.setVisibility(View.GONE);
                                secondTVAnimatiion();
                            }
                        });
            }
        }, 1500);
    }

    //SECOND TEXTVIEW ANIMATION
    public void secondTVAnimatiion() {
        secondTV.animate()
                .alpha(1f)
                .setDuration(1000);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                secondTV.animate()
                        .alpha(0f)
                        .setDuration(1000)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                secondTV.setVisibility(View.GONE);
                                thirdTVAnimation();
                            }
                        });
            }
        }, 1500);
    }

    //THIRD TEXTVIEW ANIMATION AND AFTER ANIMATION IS DONT CALL THE NEXT ACTIVITY
    public void thirdTVAnimation(){
        thirdTV.animate()
                .alpha(1f)
                .setDuration(1000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(final Animator animation) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                                intent.putExtra(getString(R.string.INMATE_DETAILS), inmateDetailsArray);
                                ActivityOptions options = ActivityOptions.makeCustomAnimation(MainActivity.this, android.R.anim.fade_in, android.R.anim.fade_out);
                                MainActivity.this.startActivity(intent, options.toBundle());
                            }
                        }, 1500);
                    }
                });
    }

    // FETCHING DATA FROM API AND CONVERTING IT O POJO
    private void getInmateDetails(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result= response.body().string();
                inmateDetailsArray = getInmateFromPojo(result);
            }
        });
    }

}
