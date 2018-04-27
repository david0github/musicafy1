package ser210.quinnipiac.edu.musicafy;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
//import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity implements View.OnClickListener {

    Button btnPassObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnPassObject = (Button) findViewById(R.id.start_button);
        btnPassObject.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){

        //create an intent pass to class name
        Intent intent = new Intent(this, MainMenuActivity.class);
        //start the activity
        startActivity(intent);

    }
}
