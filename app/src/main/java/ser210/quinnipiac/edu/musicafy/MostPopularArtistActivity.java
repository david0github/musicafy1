package ser210.quinnipiac.edu.musicafy;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MostPopularArtistActivity extends AppCompatActivity {

    dbHelper myHelper;
    RecyclerView recyclerView;
    TextView artistFirstname;
    TextView artistLastname;
    TextView homeTown;
    TextView recordLabel;
    TextView albums;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    List<ArtistData> artists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_popular_artist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Top 10 Popular Artist");

        //get passed intent
        Intent intent1 = getIntent();

        myHelper = new dbHelper(this);

        //INSERT METHODS BELOW  " ONCE " TO CREATE A DEFAULT TABLE WITH THE PRE EXISTING VALUES
        //If THE CODE BELOW IS NOT COMMENTED AFTER THE FIRST INSERTION OF ARTIST, IT WILL CONTINUE TO
        //CREATE THE SAME TEN ROWS EVERY TIME THE APP IS RUN
/**
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
*/
        artists = new ArrayList<ArtistData>();
        artists = myHelper.getAllArtists();

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);

        //use a linear layout manager
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter = new RVAdapter(this, artists);
        recyclerView.setAdapter(adapter);

    }
    @Override
    protected void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}