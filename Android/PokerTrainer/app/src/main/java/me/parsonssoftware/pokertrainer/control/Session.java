package me.parsonssoftware.pokertrainer.control;

import android.os.Bundle;

import me.parsonssoftware.pokertrainer.R;

/**
 * Created by David on 5/3/2017.
 */

public class Session {

    private int state = 0;
    private boolean[] allHands = new boolean[8];
    private boolean[] yourHands = new boolean[8];

    private final int cardBack = R.drawable.b;

    // COMMENT
    public void nextState(){

        if(state == 0){
            // This needs to be handled elsewhere
            resetHands();
        }
        if(state == 3){
            // This needs to be handled elsewhere
            clearHands();
            state = 0;
        } else {
            state += 1;
        }
    }

    // COMMENT
    private void resetHands(){
        for(int i = 0; i < 8; i++){
            allHands[i] = true;
            yourHands[i] = true;
        }
    }

    // COMMENT
    private void clearHands(){
        for(int i = 0; i < 8; i++){
            allHands[i] = false;
            yourHands[i] = false;
        }
    }

    // Returns a string representing the sessions current state.
    public String getStateRepresentation(){
        switch (state){
            case 0 :  return "Press to Start";
            case 1 : return "Turn";
            case 2 : return "River";
            case 3 : return "Finish";
            default: return "Error";
        }
    }

    // Returns integer representing the image resources of the community cards currently visible
    public int[] getCommunityImageIDs(){

        int[] ids = {R.drawable.c3, R.drawable.c6, R.drawable.h4, R.drawable.s5, R.drawable.c8};

        int count = 0;

        switch (state){
            case 0 : count = 0; break;
            case 1 : count = 3; break;
            case 2 : count = 4; break;
            case 3 : count = 5; break;
            default: count = 0;
        }

        for(int i = count; i < 5; i++){
            ids[i] = cardBack;
        }

        return ids;
    }

    // Returns integer representing the image resources of the hold cards currently visible
    public int[] getHoldImageIDs(){

        int[] ids = {R.drawable.cj, R.drawable.d9};

        int count = (state == 0 ? 0 : 2);

        for(int i = count; i < 2; i++){
            ids[i] = cardBack;
        }

        return ids;
    }

    // COMMENT
    public void removeHand(int hand, boolean isAll){
        if(isAll){
            allHands[hand] = false;
        } else {
            yourHands[hand] = false;
        }
    }

    // COMMENT
    public boolean[] getAllHands(){
        return allHands;
    }

    // COMMENT
    public boolean[] getYourHands(){
        return yourHands;
    }

}
