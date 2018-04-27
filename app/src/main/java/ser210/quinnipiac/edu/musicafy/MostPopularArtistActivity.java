package ser210.quinnipiac.edu.musicafy;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MostPopularArtistActivity extends AppCompatActivity {

    dbHelper myHelper;
    private List<ArtistData> artistData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_popular_artist);

        initializeData();

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        RVAdapter adapter = new RVAdapter(artistData);
        rv.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Top 10 Popular Artist");

        //get passed intent
        Intent intent1 = getIntent();

    }
        private void initializeData() {

        artistData = new ArrayList<>();
        myHelper = new dbHelper(this);

        artistData.add(new ArtistData("Jason", "Aldean", "http://s3.amazonaws.com/factmag-images/wp-content/uploads/2017/03/screen-shot-2016-01-14-at-9-15-37-pm-616x4401.png",
                    "Macon, GA", "Broken Bow Records", "My Kinda Party, Old Boots New Dirt, Wid Open"));

        artistData.add(new ArtistData("Cardi", "B", "http://s3.amazonaws.com/factmag-images/wp-content/uploads/2017/03/screen-shot-2016-01-14-at-9-15-37-pm-616x4401.png",
                    "The Bronx, NY", "Atlantic Records", "Gangsta Bitch Music Vol 2, Gangsta bitch Music Vol 1, Invasion of Privacy"));

        artistData.add(new ArtistData("Drake", "", "http://s3.amazonaws.com/factmag-images/wp-content/uploads/2017/03/screen-shot-2016-01-14-at-9-15-37-pm-616x4401.png",
                    "Toronto, Canada", "OVO Sound", "If You're Reading This Its Too Late, So Far Gone, Take Care"));

        artistData.add(new ArtistData("Breaking", "Benjamin", "http://s3.amazonaws.com/factmag-images/wp-content/uploads/2017/03/screen-shot-2016-01-14-at-9-15-37-pm-616x4401.png",
                    "Wilkes-Barre, PA", "Hollywood Records", "Dear Agony, Dark Before Dawn, Phobia"));

        artistData.add(new ArtistData("Imagine", "Dragons", "http://s3.amazonaws.com/factmag-images/wp-content/uploads/2017/03/screen-shot-2016-01-14-at-9-15-37-pm-616x4401.png",
                    "Las Vegas, NV", "Interscope Records", "Evolve, Smoke + Mirrors, Night Visions"));

        artistData.add(new ArtistData("Ed", "Sheeran", "http://s3.amazonaws.com/factmag-images/wp-content/uploads/2017/03/screen-shot-2016-01-14-at-9-15-37-pm-616x4401.png",
                    "Halifax, United Kingdom", "Warner Music Group", "Divide, X, The Orange Room EP"));

        artistData.add(new ArtistData("The", "Weeknd", "http://s3.amazonaws.com/factmag-images/wp-content/uploads/2017/03/screen-shot-2016-01-14-at-9-15-37-pm-616x4401.png",
                    "Toronto, Canada", "XO Records", "Starboy, My Dear  Melancholy, Beauty Behind the Madness"));

        artistData.add(new ArtistData("Post", "Malone", "http://s3.amazonaws.com/factmag-images/wp-content/uploads/2017/03/screen-shot-2016-01-14-at-9-15-37-pm-616x4401.png",
                    "Syracuse, NY", "Republic Records", "Stoney, Beerbongs & Bentleys, August 26th"));

        artistData.add(new ArtistData("Kendrick", "Lamar", "https://at-cdn-s01.audiotool.com/2015/02/13/documents/i_never_would_kendrick_lamar_drake_travis_scott_type_beat_prod_by_radical_behavior/1/cover256x256-febd6fba799d4bef80c472d063e40867.jpg",
                "Compton, CA", "Top Dawg Entertainment", "DAMN. , To Pimp A Butterfly and good kid, m.A.A.d city"));

        artistData.add(new ArtistData("John", "Prine", "http://s3.amazonaws.com/factmag-images/wp-content/uploads/2017/03/screen-shot-2016-01-14-at-9-15-37-pm-616x4401.png",
                    "Maywood, IL", "Top Dawg Entertainment", "In Spite of Ourselves, The Missing Years, For Better or Worse"));

        }


}
