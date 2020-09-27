package com.janithbinara.themovie;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class SecondActivity extends AppCompatActivity {

    String backdropPath,title,overview,voteAverage,releaseDate,originalLanguage,originalTitle,
            popularity,voteCount;
    ImageView backImage;
    TextView title_txt,overview_txt,voteAverage_txt,releaseDate_txt,originalLanguage_txt,
            originalTitle_txt,popularity_txt,voteCount_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        backdropPath = extras.getString("backdropPath");
        title = extras.getString("title");
        overview = extras.getString("overview");
        voteAverage = extras.getString("voteAverage");
        releaseDate = extras.getString("releaseDate");
        originalLanguage = extras.getString("originalLanguage");
        originalTitle = extras.getString("originalTitle");
        popularity = extras.getString("popularity");
        voteCount = extras.getString("voteCount");

        backImage = findViewById(R.id.backImage);
        String imagePath = "https://image.tmdb.org/t/p/w500"+backdropPath;
        Glide.with(this)
                .load(imagePath)
                .placeholder(R.drawable.cover_image)
                .error(R.drawable.cover_image)
                .into(backImage);

        title_txt = findViewById(R.id.title_txt);
        title_txt.setText(title);
        getSupportActionBar().setTitle(title);

        overview_txt = findViewById(R.id.overview_txt);
        overview_txt.setText(overview);

        voteAverage_txt = findViewById(R.id.voteAverage_txt);
        voteAverage_txt.setText(voteAverage);

        releaseDate_txt = findViewById(R.id.releaseDate_txt);
        releaseDate_txt.setText("Release Date: "+releaseDate);

        originalLanguage_txt = findViewById(R.id.originalLanguage_txt);
        originalLanguage_txt.setText("Original Language: "+originalLanguage);

        originalTitle_txt = findViewById(R.id.originalTitle_txt);
        originalTitle_txt.setText("Original Name: "+originalTitle);

        popularity_txt = findViewById(R.id.popularity_txt);
        popularity_txt.setText("Popularity: "+popularity);

        voteCount_txt = findViewById(R.id.voteCount_txt);
        voteCount_txt.setText("("+voteCount+")");

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}