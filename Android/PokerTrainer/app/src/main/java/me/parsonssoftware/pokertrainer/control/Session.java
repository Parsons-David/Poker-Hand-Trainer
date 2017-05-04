package me.parsonssoftware.pokertrainer.control;

import android.os.Bundle;

import me.parsonssoftware.pokertrainer.R;

/**
 * Created by David on 5/3/2017.
 */

public class Session {

    private int state = 0;

    private final int cardBack = R.drawable.b;

    public Bundle nextState(){
        Bundle currentState = new Bundle();

        currentState.putInt("CardBack", cardBack);

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

        currentState.putString("State", stateRep);
        currentState.putInt("cCount", cCount);
        currentState.putInt("hCount", hCount);
        currentState.putIntArray("Cids", CIds);;
        currentState.putIntArray("Hids", HIds);

        state += 1;
        return currentState;
    }
}
