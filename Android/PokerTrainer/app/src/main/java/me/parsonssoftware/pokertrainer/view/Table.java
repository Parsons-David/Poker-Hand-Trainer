package me.parsonssoftware.pokertrainer.view;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import me.parsonssoftware.pokertrainer.R;
import me.parsonssoftware.pokertrainer.control.Session;

public class Table extends Activity {

    private Button btnState;

    private ImageView[] communityImages = new ImageView[5];
    private ImageView[] holdImages = new ImageView[2];

    private Session session = new Session();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);

        btnState = (Button) findViewById(R.id.btnState);

        communityImages[0] = (ImageView) findViewById(R.id.imgViewC1);
        communityImages[1] = (ImageView) findViewById(R.id.imgViewC2);
        communityImages[2] = (ImageView) findViewById(R.id.imgViewC3);
        communityImages[3] = (ImageView) findViewById(R.id.imgViewC4);
        communityImages[4] = (ImageView) findViewById(R.id.imgViewC5);

        holdImages[0] = (ImageView) findViewById(R.id.imgViewH1);
        holdImages[1] = (ImageView) findViewById(R.id.imgViewH2);

    }

    // Action Handler for State Button on Table
    public void onStatePress(View v){

        // Assures the Train Button is being pressed
        if((Button) v != btnState){
            Toast.makeText(getApplicationContext(), "This Button Should not Change State", Toast.LENGTH_SHORT).show();
        }

        // Gets state date from current session
        Bundle newState = session.nextState();

        // Updates State Button
        btnState.setText(newState.getString("State"));


        // Updates Community Card Images
        for(int i = 0; i < newState.getInt("cCount"); i++){
            communityImages[i].setImageResource(newState.getIntArray("Cids")[i]);
        }
        for(int i = newState.getInt("cCount"); i < 5; i++){
            communityImages[i].setImageResource(newState.getInt("CardBack"));
        }

        // Updates Hold Card Images
        for(int i = 0; i < newState.getInt("hCount"); i++){
            holdImages[i].setImageResource(newState.getIntArray("Hids")[i]);
        }
        for(int i = newState.getInt("hCount"); i < 2; i++){
            holdImages[i].setImageResource(newState.getInt("CardBack"));
        }

    }

}
