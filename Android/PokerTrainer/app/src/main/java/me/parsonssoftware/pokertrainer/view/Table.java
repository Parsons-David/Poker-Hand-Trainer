package me.parsonssoftware.pokertrainer.view;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import me.parsonssoftware.pokertrainer.R;
import me.parsonssoftware.pokertrainer.control.Session;

public class Table extends Activity {

    private Button btnState;
    private Button btnToggleHands;
    private ArrayList<Button> btnHands = new ArrayList<>();


    private ImageView[] communityImages = new ImageView[5];
    private ImageView[] holdImages = new ImageView[2];

    private Session session = new Session();
    private boolean viewingAll = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);

        btnState = (Button) findViewById(R.id.btnState);
        btnToggleHands = (Button) findViewById(R.id.btnToggleHands);

        btnHands.add((Button) findViewById(R.id.btnSF));
        btnHands.add((Button) findViewById(R.id.btnFH));
        btnHands.add((Button) findViewById(R.id.btnStr));
        btnHands.add((Button) findViewById(R.id.btnTP));
        btnHands.add((Button) findViewById(R.id.btnFK));
        btnHands.add((Button) findViewById(R.id.btnFl));
        btnHands.add((Button) findViewById(R.id.btnTK));
        btnHands.add((Button) findViewById(R.id.btnP));

        for(Button btn : btnHands){
            btn.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    onHandPress(v);
                }
            });
        }

        updateHandButtons();

        communityImages[0] = (ImageView) findViewById(R.id.imgViewC1);
        communityImages[1] = (ImageView) findViewById(R.id.imgViewC2);
        communityImages[2] = (ImageView) findViewById(R.id.imgViewC3);
        communityImages[3] = (ImageView) findViewById(R.id.imgViewC4);
        communityImages[4] = (ImageView) findViewById(R.id.imgViewC5);

        holdImages[0] = (ImageView) findViewById(R.id.imgViewH1);
        holdImages[1] = (ImageView) findViewById(R.id.imgViewH2);


        session.nextState();
    }

    // Action Handler for State Button on Table
    public void onStatePress(View v){

        // Assures the Train Button is being pressed
        if((Button) v != btnState){
            Toast.makeText(getApplicationContext(), "This Button Should not Change State", Toast.LENGTH_SHORT).show();
            return;
        }

        // Updates State Button
        btnState.setText(session.getStateRepresentation());

        // Updates Community Card Images
        for(int i = 0; i < 5; i++){
            communityImages[i].setImageResource(session.getCommunityImageIDs()[i]);
        }

        // Updates Hold Card Images
        for(int i = 0; i < 2; i++){
            holdImages[i].setImageResource(session.getHoldImageIDs()[i]);
        }

        updateHandButtons();

        session.nextState();

    }

    // Action Handler for Toggle Hand Button on Table
    public void onToggleHandsPress(View v){

        // Assures the Toggle Button is being pressed
        if((Button) v != btnToggleHands){
            Toast.makeText(getApplicationContext(), "This Button Should not Toggle Hands", Toast.LENGTH_SHORT).show();
            return;
        }

        // Updates what is being viewed
        viewingAll = !viewingAll;

        // Toggles Test Based on what is currently being viewed
        btnToggleHands.setText(viewingAll ? R.string.allHands : R.string.yourHands );

        updateHandButtons();
    }


    // COMMENT
    public void onHandPress(View v){
        Button pressed = (Button) v;
        // Assures the Toggle Button is being pressed
        if(! btnHands.contains(pressed)){
            Toast.makeText(getApplicationContext(), "This Button Should not Toggle Hands", Toast.LENGTH_SHORT).show();
            return;
        }

        pressed.setEnabled(false);

        session.removeHand(btnHands.indexOf(pressed), viewingAll);

    }

    // COMMENT
    private void updateHandButtons(){

        boolean[] visibility = (viewingAll ? session.getAllHands() : session.getYourHands() );

        for(int i = 0; i < visibility.length; i++){
            btnHands.get(i).setEnabled(visibility[i]);
        }

    }

}
