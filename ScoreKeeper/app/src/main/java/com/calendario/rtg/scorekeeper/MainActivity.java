package com.calendario.rtg.scorekeeper;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TRACKLIST_NAME = "TRACKLIST_NAME";
    private static final String RECYCLER_NAME = "RECYCLER_NAME";
    private static final String SCORE_T1_NAME = "SCORE_T1_NAME";
    private static final String SCORE_T2_NAME = "SCORE_T2_NAME";
    private TextView mScoreT1, mScoreT2;
    private Button mAddToScore;
    private Button mTrackFoul;
    private Button mYellowCardButton;
    private Button mRedCardButton;
    private Button mResetButton;
    private RecyclerView mRecyclerViewTrack;
    private List<String> mTrackList;
    private List<Card> mYellowCardList;
    private List<Card> mRedCardList;
    private EditText mEditMin;
    private EditText mEditPlayerNum;
    private RecyclerAdapterTrack mRecyclerAdapter;
    private CheckBox mCheckBoxPenalty;
    private RadioButton mT1Selection;
    private Bundle myCustomBundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            myCustomBundle = savedInstanceState;
        }

        mTrackList = new ArrayList<>();
        mYellowCardList = new ArrayList<>();
        mRedCardList = new ArrayList<>();

        mResetButton = findViewById(R.id.button_reset);

        mAddToScore = findViewById(R.id.button_add);

        mCheckBoxPenalty = findViewById(R.id.checkbox_penalty);

        mTrackFoul = findViewById(R.id.button_foul);

        mEditMin = findViewById(R.id.edit_min);

        mEditPlayerNum = findViewById(R.id.edit_player);

        mT1Selection = findViewById(R.id.radiobutton_t1);

        mYellowCardButton = findViewById(R.id.button_yellowcard);
        mRedCardButton = findViewById(R.id.button_redcard);

        mScoreT1 = findViewById(R.id.text_score_team_1);
        mScoreT2 = findViewById(R.id.text_score_team_2);

        mRecyclerViewTrack = findViewById(R.id.recycler_track);
        mRecyclerViewTrack.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerAdapter = new RecyclerAdapterTrack(mTrackList);
        mRecyclerViewTrack.setAdapter(mRecyclerAdapter);

        mAddToScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mEditMin.getText().toString().equals("") && !mEditPlayerNum.getText().toString().equals("")) {

                    if(mCheckBoxPenalty.isChecked()) {
                        if (mT1Selection.isChecked()){
                            setMessage(1,"1");
                        }
                        else {
                            setMessage(1,"2");
                        }
                        mCheckBoxPenalty.setChecked(false);
                    }
                    else{
                        if (mT1Selection.isChecked()) {
                            setMessage(2, "1");
                        }
                        else {
                            setMessage(2, "2");
                        }
                    }
                    if(mT1Selection.isChecked()){
                        goal(1);
                    }
                    else{
                        goal(2);
                    }
                    mRecyclerAdapter.notifyDataSetChanged();
                    mEditMin.setText("");
                    mEditPlayerNum.setText("");
                }


            }
        });

        mTrackFoul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mEditMin.getText().toString().equals("") && !mEditPlayerNum.getText().toString().equals("")) {
                    if (mT1Selection.isChecked()) {
                        foul("1");
                    }
                    else {
                        foul("2");
                    }
                    mRecyclerAdapter.notifyDataSetChanged();
                    mEditMin.setText("");
                    mEditPlayerNum.setText("");
                }
            }
        });

        mYellowCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEditMin.getText().toString().equals("") && !mEditPlayerNum.getText().toString().equals("")) {
                    if (mT1Selection.isChecked()) {
                        if (!getCards(2, 1, mEditPlayerNum.getText().toString())) {
                            card(1,"1");
                            if (getCards(1, 1, mEditPlayerNum.getText().toString())) {
                                card(2,"0");
                                mRedCardList.add(new Card("T1", mEditPlayerNum.getText().toString()));
                            } else {
                                mYellowCardList.add(new Card("T1", mEditPlayerNum.getText().toString()));
                            }
                            mRecyclerAdapter.notifyDataSetChanged();
                            mEditMin.setText("");
                            mEditPlayerNum.setText("");
                        }
                        else {
                            Snackbar.make(v,"Player is not longer in the game!", Snackbar.LENGTH_SHORT).show();
                        }
                    } else {
                        if (!getCards(2, 2, mEditPlayerNum.getText().toString())) {
                            card(1,"2");
                            if (getCards(1, 2, mEditPlayerNum.getText().toString())) {
                                card(2, "0");
                                mRedCardList.add(new Card("T2", mEditPlayerNum.getText().toString()));
                            } else {
                                mYellowCardList.add(new Card("T2", mEditPlayerNum.getText().toString()));
                            }
                            mRecyclerAdapter.notifyDataSetChanged();
                            mEditMin.setText("");
                            mEditPlayerNum.setText("");
                        }
                        else{
                            Snackbar.make(v,"Player is not longer in the game!", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        mRedCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mEditMin.getText().toString().equals("") && !mEditPlayerNum.getText().toString().equals("")) {
                    if (mT1Selection.isChecked()) {
                        if (!getCards(2, 1, mEditPlayerNum.getText().toString())) {
                            mRedCardList.add(new Card("T1", mEditPlayerNum.getText().toString()));
                            card(3,"1");
                            mRecyclerAdapter.notifyDataSetChanged();
                            mEditMin.setText("");
                            mEditPlayerNum.setText("");
                        }
                        else {
                            Snackbar.make(v,"Player is not longer in the game!", Snackbar.LENGTH_SHORT).show();
                        }
                    } else {
                        if (!getCards(2, 2, mEditPlayerNum.getText().toString())) {
                            mRedCardList.add(new Card("T2", mEditPlayerNum.getText().toString()));
                            card(3,"2");
                            mRecyclerAdapter.notifyDataSetChanged();
                            mEditMin.setText("");
                            mEditPlayerNum.setText("");
                        }
                        else {
                            Snackbar.make(v,"Player is not longer in the game!", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScoreT1.setText("0");
                mScoreT2.setText("0");
                mYellowCardList.clear();
                mRedCardList.clear();
                mTrackList.clear();
                mRecyclerAdapter.notifyDataSetChanged();
                mCheckBoxPenalty.setChecked(false);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(myCustomBundle != null) {
            mRecyclerAdapter = new RecyclerAdapterTrack(mTrackList);
            mRecyclerViewTrack.setAdapter(mRecyclerAdapter);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCORE_T1_NAME,Integer.parseInt(mScoreT1.getText().toString()));
        outState.putInt(SCORE_T2_NAME,Integer.parseInt(mScoreT2.getText().toString()));
        outState.putParcelable(TRACKLIST_NAME,mRecyclerViewTrack.getLayoutManager().onSaveInstanceState());
        outState.putStringArrayList(RECYCLER_NAME, (ArrayList<String>) mTrackList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null) {
            mRecyclerViewTrack.getLayoutManager().onRestoreInstanceState(savedInstanceState.getParcelable(TRACKLIST_NAME));
            mScoreT1.setText(savedInstanceState.get(SCORE_T1_NAME).toString());
            mScoreT2.setText(savedInstanceState.get(SCORE_T2_NAME).toString());
            mTrackList = savedInstanceState.getStringArrayList(RECYCLER_NAME);
        }
    }

    /**
     * Method for checking the current cards that a player has yellow or red
     * @param typeOfCard which card do you want 1 yellow, 2 for red
     * @param team 1 for team 1 2 for team 2
     * @param player which player has the card
     * @return returns a boolean if the player consulted has a card
     */
    public boolean getCards(int typeOfCard, int team,String player){
        Card currentCard;
        switch(typeOfCard){
            case 1:
                switch (team){
                    case 1:
                        currentCard = new Card("T1",player);
                        for (Card card : mYellowCardList) {
                            if(card.getPlayer().equals(currentCard.getPlayer()) && card.getTeam().equals(currentCard.getTeam())){
                                return true;
                            }

                        }
                        return false;
                    case 2:
                        currentCard = new Card("T2",player);
                        for (Card card : mYellowCardList) {
                            if(card.getPlayer().equals(currentCard.getPlayer()) && card.getTeam().equals(currentCard.getTeam())){
                                return true;
                            }

                        }
                        return false;
                }
            case 2:
                switch (team){
                    case 1:
                        currentCard = new Card("T1",player);
                        for (Card card : mRedCardList) {
                            if(card.getPlayer().equals(currentCard.getPlayer()) && card.getTeam().equals(currentCard.getTeam())){
                                return true;
                            }
                        }
                        return false;
                    case 2:
                        currentCard = new Card("T2",player);
                        for (Card card : mRedCardList) {
                            if(card.getPlayer().equals(currentCard.getPlayer()) && card.getTeam().equals(currentCard.getTeam())){
                                return true;
                            }
                        }
                        return false;
                }
        }
        return false;
    }

    /**
     * Method for seting the message
     * @param messageType 1 for Penalty message 2 for goal message
     * @param team team to be displays 1 for team 1 2 for team 2
     */
    public void setMessage(int messageType, String team){
        String message = "";
        switch (messageType) {
            case 1:
                message = String.format(mEditMin.getText().toString() + "'" + " Penalty Goal! by player number " + mEditPlayerNum.getText().toString() + " (Team %s). \n", team);
                mTrackList.add(message);
                break;
            case 2:
                message = String.format(mEditMin.getText().toString() + "'" + " Goal! by player number " + mEditPlayerNum.getText().toString() + " (Team %s). ", team);
                mTrackList.add(message);
        }
    }

    /**
     * Method for adding a goal to the score
     * @param messageType 1 for team 1 , 2 for team 2
     */
    public void goal(int messageType){
        int currentScore;

        switch (messageType){
            case 1:
                currentScore = Integer.parseInt(mScoreT1.getText().toString());
                mScoreT1.setText(String.valueOf(currentScore + 1));
                break;
            case 2:
                currentScore = Integer.parseInt(mScoreT2.getText().toString());
                mScoreT2.setText(String.valueOf(currentScore + 1));
                break;
        }
    }

    /**
     * Displays the message of foul
     * @param team 1 for team 1 2 for team 2
     */
    public void foul(String team){
        String message;
        message = String.format(mEditMin.getText().toString() + "'" + " Foul by player number " + mEditPlayerNum.getText().toString() + " (Team %s) ", team);
        mTrackList.add(message);
    }

    /**
     * Method for siplaying type of card
     * @param messageType 1 for yellow card, 2 for red card for accumulation of yellow cards, 3 direct red card
     * @param team 1 for team 1 2 for team 2
     */
    public void card(int messageType, String team){
        String message = "";
        switch (messageType){
            case 1:
                message = String.format(mEditMin.getText().toString() + "'" + " Yellow card for player number " + mEditPlayerNum.getText().toString() + " (Team %s) ", team);
                mTrackList.add(message);
                break;
            case 2:
                message = mEditMin.getText().toString() + "'" + " Red card. Player receives second yellow card ";
                mTrackList.add(message);
                break;
            case 3:
                message = String.format(mEditMin.getText().toString() + "'" + " Red card for player number " + mEditPlayerNum.getText().toString() + " (Team %s) ", team);
                mTrackList.add(message);
                break;

        }
    }
}
