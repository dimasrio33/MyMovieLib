package com.example.user.mymovielib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    public TextView txtTitle, txtsynopsis, txtdate, txtscore;
    public ImageView bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText(getIntent().getStringExtra("title"));

        txtdate = (TextView) findViewById(R.id.txtDate);
        txtdate.setText(getIntent().getStringExtra("date"));

        txtsynopsis = (TextView) findViewById(R.id.txtSynopsis);
        txtsynopsis.setText(getIntent().getStringExtra("overview"));

        txtscore = (TextView) findViewById(R.id.txtRating);
        txtscore.setText(getIntent().getStringExtra("vote"));

        bg = (ImageView) findViewById(R.id.bg);
        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w780" + getIntent().getStringExtra("bg"))
                .resize(1200, 700)
                .centerCrop()
                .into(bg);
    }
}

