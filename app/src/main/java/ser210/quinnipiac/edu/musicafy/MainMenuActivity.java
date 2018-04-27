package ser210.quinnipiac.edu.musicafy;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
//import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button artistBtn;

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
