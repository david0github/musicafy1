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