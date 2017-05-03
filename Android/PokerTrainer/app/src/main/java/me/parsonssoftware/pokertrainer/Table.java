package me.parsonssoftware.pokertrainer;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Table extends Activity {

    private Button btnState;

    private ImageView[] communityImages = new ImageView[5];
    private ImageView[] holdImages = new ImageView[2];

    private int state = 0;

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

        Bundle data = nextState();

        btnState.setText(data.getString("State"));



        for(int i = 0; i < data.getInt("cCount"); i++){
            communityImages[i].setImageResource(data.getIntArray("Cids")[i]);
        }
        for(int i = data.getInt("cCount"); i < 5; i++){
            communityImages[i].setImageResource(R.drawable.b);
        }

        for(int i = 0; i < data.getInt("hCount"); i++){
            holdImages[i].setImageResource(data.getIntArray("Hids")[i]);
        }
        for(int i = data.getInt("hCount"); i < 2; i++){
            holdImages[i].setImageResource(R.drawable.b);
        }

    }

    public Bundle nextState(){
        Bundle data = new Bundle();
        String stateRep;
        int cCount = 0;
        int hCount = 2;

        int[] CIds = {R.drawable.c3, R.drawable.c6, R.drawable.h4, R.drawable.s5, R.drawable.c8};
        int[] HIds = {R.drawable.cj, R.drawable.d9};

        switch (state){
            case 0 : stateRep = "Turn"; cCount = 3; break;
            case 1 : stateRep = "River"; cCount = 4; break;
            case 2 : stateRep = "Finish"; cCount = 5; break;
            case 3 : state = -1; stateRep = "Press to Start"; cCount = 0; hCount = 0; break;
            default: stateRep = "Error"; cCount = 0;
        }

        data.putString("State", stateRep);
        data.putInt("cCount", cCount);
        data.putInt("hCount", hCount);
        data.putIntArray("Cids", CIds);;
        data.putIntArray("Hids", HIds);

        state += 1;
        return data;
    }
}
