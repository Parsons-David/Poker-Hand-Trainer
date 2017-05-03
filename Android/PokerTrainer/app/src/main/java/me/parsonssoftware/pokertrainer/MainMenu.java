package me.parsonssoftware.pokertrainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainMenu extends Activity {

    private Button btnTrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        btnTrain = (Button) findViewById(R.id.btnTrain);

    }

    // Action Handler for Train Button on Main Menu
    public void onTrainPress(View v){

        // Assures the Train Button is being pressed
        if((Button) v != btnTrain){
            Toast.makeText(getApplicationContext(), "This Button Should not Launch Table.", Toast.LENGTH_SHORT).show();
        }

        showTable();

    }

    // Move to Table Activity
    void showTable(){
        Intent intent = new Intent(this, Table.class);
        startActivity(intent);
    }

}
