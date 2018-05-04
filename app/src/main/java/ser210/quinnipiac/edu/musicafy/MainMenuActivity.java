package ser210.quinnipiac.edu.musicafy;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
//import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button artistBtn;
    Toolbar toolbar;
    private ShareActionProvider mShareActionProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Selection");

        artistBtn = (Button) findViewById(R.id.search_button_0);
        artistBtn.setOnClickListener(this);

        //get passed intent from HomeActivity
        Intent intent = getIntent();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);

        return true;
    }

    //share option
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_share:
                Intent i = new Intent(
                        android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(
                        android.content.Intent.EXTRA_TEXT, "My new app https://play.google.com/store/search?q=TECHUBINDIAN"
                );
                startActivity(Intent.createChooser(
                        i,
                        "Share Via"));
                break;
        }

        Toast.makeText(getApplicationContext(), "You click on menu share", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);

    }

    public void onSendMessage(View view){
        EditText messageView = findViewById(R.id.send_message);
        //get the text form the editable text field with an id of message
        String messageText = messageView.getText().toString();
        //create an intent pass to class SearchedSongActivity
        Intent intent = new Intent(this, SearchedSongActivity.class);
        //add the text to the intent giving it a name of "message"
        intent.putExtra("message", messageText);
        //start the activity
        startActivity(intent);

    }

   @Override
    public void onClick(View view){
        //create an intent pass to class MostPopularArtistActivity
        Intent intent1 = new Intent(this, MostPopularArtistActivity.class);
        //start the activity
        startActivity(intent1);

    }
}
