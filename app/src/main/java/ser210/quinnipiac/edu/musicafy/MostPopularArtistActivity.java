package ser210.quinnipiac.edu.musicafy;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MostPopularArtistActivity extends AppCompatActivity {

    private dbHelper myHelper;
    private TextView artistFirstname;
    private TextView artistLastname;
    private TextView homeTown;
    private TextView recordLabel;
    private TextView albums;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayManager;
    private RVAdapter adapter;
    private String filter = "";
    private List<ArtistData> artists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_popular_artist);

        //initializeData();

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mRecyclerView.setHasFixedSize(true);

        //use a linear layout manager
        mLayManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayManager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Top 10 Popular Artist");

        populaterecyclerView(filter);

        //get passed intent
        Intent intent1 = getIntent();

        myHelper = new dbHelper(this);

        myHelper.insertArtist("Jason", "Aldean",
                "Macon, GA", "Broken Bow Records", "My Kinda Party, Old Boots New Dirt, Wid Open");

        myHelper.insertArtist("Cardi", "B",
                "The Bronx, NY", "Atlantic Records", "Gangsta Bitch Music Vol 2, Gangsta bitch Music Vol 1, Invasion of Privacy");

        myHelper.insertArtist("Drake", "",
                "Toronto, Canada", "OVO Sound", "If You're Reading This Its Too Late, So Far Gone, Take Care");

        myHelper.insertArtist("Breaking", "Benjamin",
                "Wilkes-Barre, PA", "Hollywood Records", "Dear Agony, Dark Before Dawn, Phobia");

        myHelper.insertArtist("Imagine", "Dragons",
                "Las Vegas, NV", "Interscope Records", "Evolve, Smoke + Mirrors, Night Visions");

        myHelper.insertArtist("Ed", "Sheeran",
                "Halifax, United Kingdom", "Warner Music Group", "Divide, X, The Orange Room EP");

        myHelper.insertArtist("The", "Weeknd",
                "Toronto, Canada", "XO Records", "Starboy, My Dear  Melancholy, Beauty Behind the Madness");

        myHelper.insertArtist("Post", "Malone",
                "Syracuse, NY", "Republic Records", "Stoney, Beerbongs & Bentleys, August 26th");

        myHelper.insertArtist("Kendrick", "Lamar",
                "Compton, CA", "Top Dawg Entertainment", "DAMN. , To Pimp A Butterfly and good kid, m.A.A.d city");

        myHelper.insertArtist("J.", "Cole",
                "Frankfurt, Germany", "Dreamville Records", "2014 Forest Hills Drive, Born Sinner, KOD");

        artists = myHelper.getAllArtists();

/**
        //inserting artist/rows
        Log.d("Insert:","Inserting...");
        db.addArtist(new ArtistData(0, "Jason", "Aldean",
                "Macon, GA", "Broken Bow Records", "My Kinda Party, Old Boots New Dirt, Wid Open"));

        //reading all artists
        Log.d("Reading:", "Reading all artists...");
        List<ArtistData> artists = db.getAllArtists();

        for (ArtistData artistData : artists){
            String log = "Id:" + artistData.getId() + ", First Name: " + artistData.getArtistFirstName()
                    + ", Last Name: " + artistData.getArtistLastName() + ", Hometown: " + artistData.getHomeTown()
                    + ", Record Label: " + artistData.getRecordLabel() + ", Albums: " + artistData.getAlbums();

            //writing artist to log
            Log.d("Artist: ", log);
        } */
    }

    private void populaterecyclerView(String filter){
        myHelper = new dbHelper(this);
        adapter = new RVAdapter(myHelper.artistList(filter), this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);
    }
    /**
    private void initializeData() {

        artistData = new ArrayList<>();
        myHelper = new dbHelper(this);

        artistData.add(new ArtistData("Jason", "Aldean",
                "Macon, GA", "Broken Bow Records", "My Kinda Party, Old Boots New Dirt, Wid Open"));

        artistData.add(new ArtistData("Cardi", "B",
                "The Bronx, NY", "Atlantic Records", "Gangsta Bitch Music Vol 2, Gangsta bitch Music Vol 1, Invasion of Privacy"));

        artistData.add(new ArtistData("Drake", "",
                "Toronto, Canada", "OVO Sound", "If You're Reading This Its Too Late, So Far Gone, Take Care"));

        artistData.add(new ArtistData("Breaking", "Benjamin",
                "Wilkes-Barre, PA", "Hollywood Records", "Dear Agony, Dark Before Dawn, Phobia"));

        artistData.add(new ArtistData("Imagine", "Dragons",
                "Las Vegas, NV", "Interscope Records", "Evolve, Smoke + Mirrors, Night Visions"));

        artistData.add(new ArtistData("Ed", "Sheeran",
                "Halifax, United Kingdom", "Warner Music Group", "Divide, X, The Orange Room EP"));

        artistData.add(new ArtistData("The", "Weeknd",
                "Toronto, Canada", "XO Records", "Starboy, My Dear  Melancholy, Beauty Behind the Madness"));

        artistData.add(new ArtistData("Post", "Malone",
                "Syracuse, NY", "Republic Records", "Stoney, Beerbongs & Bentleys, August 26th"));

        artistData.add(new ArtistData("Kendrick", "Lamar",
                "Compton, CA", "Top Dawg Entertainment", "DAMN. , To Pimp A Butterfly and good kid, m.A.A.d city"));

        artistData.add(new ArtistData("John", "Prine",
                "Maywood, IL", "Top Dawg Entertainment", "In Spite of Ourselves, The Missing Years, For Better or Worse"));

    } */

    @Override
    protected void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}