package com.example.user.mymovielib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.user.mymovielib.Adapter.MvAdapter;
import com.example.user.mymovielib.Model.Mv;
import com.example.user.mymovielib.Model.MvResponse;
import com.example.user.mymovielib.Rest.ApiClient;
import com.example.user.mymovielib.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "ada71d653c969073f2cd609d9eeb1e5d";

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==R.id.setting){
            startActivity(new Intent(this, SettingActivity.class));

        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movie_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MvResponse> call = apiService.getNowPlaying(API_KEY);
        call.enqueue(new Callback<MvResponse>() {
            @Override
            public void onResponse(Call<MvResponse>call, Response<MvResponse> response) {
                final List<Mv> movies = response.body().getResults();
                Log.d(TAG, "Number of movies received: " + movies.size());
                Toast.makeText(MainActivity.this, "Number of movies received: " + movies.size(), Toast.LENGTH_LONG).show();
                recyclerView.setAdapter(new MvAdapter(movies, R.layout.list_movie, getApplicationContext()));
                /*perintah klik recyclerview*/
                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                    GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                        public boolean onSingleTapUp(MotionEvent e){
                            return true;
                        }
                    });

                    @Override
                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                        View child = rv.findChildViewUnder(e.getX(), e.getY());
                        if (child != null && gestureDetector.onTouchEvent(e)){
                            int position = rv.getChildAdapterPosition(child);
                            /*Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                            i.putExtra("id", movies.get(position).getId());
                            getApplicationContext().startActivity(i);*/
                            Toast.makeText(getApplicationContext(), "Id : " + movies.get(position).getId() + " selected", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(MainActivity.this, DetailActivity.class);
                            i.putExtra("title", movies.get(position).getTitle());
                            i.putExtra("date", movies.get(position).getReleaseDate());
                            i.putExtra("vote", movies.get(position).getVoteAverage().toString());
                            i.putExtra("overview", movies.get(position).getOverview());
                            i.putExtra("bg", movies.get(position).getPosterPath());
                            MainActivity.this.startActivity(i);


                        }
                        return false;
                    }

                    @Override
                    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                    }

                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                    }
                });
            }

            @Override
            public void onFailure(Call<MvResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });


    }
}
