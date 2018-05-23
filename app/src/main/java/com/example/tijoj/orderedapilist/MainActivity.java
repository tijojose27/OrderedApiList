package com.example.tijoj.orderedapilist;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tijoj.orderedapilist.POJO.InmatePOJO;
import com.example.tijoj.orderedapilist.POJOHelpers.POJOHelper;

import org.apache.http.conn.scheme.HostNameResolver;
import org.w3c.dom.Text;

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

        firstTV = findViewById(R.id.first_intro_text_view_main);
        secondTV = findViewById(R.id.second_intro_text_view_main);
        thirdTV = findViewById(R.id.third_intro_text_view_main);

        final String url=getString(R.string.URL);

        secondTV.setAlpha(0f);
        thirdTV.setAlpha(0f);

        getInmateDetails(url);

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
}
