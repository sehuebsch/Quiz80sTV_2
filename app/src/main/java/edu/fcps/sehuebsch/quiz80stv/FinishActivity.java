package edu.fcps.sehuebsch.quiz80stv;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FinishActivity extends AppCompatActivity {


    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ChildEventListener leaderListener;
    private ArrayList<LeaderEntry> leaderList;
    private ArrayList<LeaderEntry> searchResults;
    private LeaderAdapter mLeaderAdapter;
    private RecyclerView mResults;
    private LinearLayoutManager mLayoutManager;

    private static final String EXTRA_POINTS_DONE =
            "edu.fcps.sehuebsch.quiz80stv.points_done";
    private TextView mScoreText;
    private EditText mNameEntry;
    private TextView mCongratsText;
    private Button mSaveButton;
    private int mPoints;

    private LeaderEntry[] leaderBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("highscore");
        myRef = database.getReference("leaderboard");
        leaderList = new ArrayList<LeaderEntry>();
        searchResults = new ArrayList<LeaderEntry>();
        leaderList.add(new LeaderEntry("Fake One", 10));

        leaderListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                leaderList.add(dataSnapshot.getValue(LeaderEntry.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        /*
        //If this is the first score, add it!
        if (leaderList.size() == 0)
        */
        myRef.addChildEventListener(leaderListener);
        Log.i("MEMEMEME", leaderList.toString());
        mLeaderAdapter = new LeaderAdapter(leaderList);
        mResults  = (RecyclerView)findViewById(R.id.leaderboard_list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mResults.setHasFixedSize(true);

        // use a linear layout manager
        //mLayoutManager = new LinearLayoutManager(this);
        //mResults.setLayoutManager(mLayoutManager);
        mResults.setAdapter(mLeaderAdapter);

        mScoreText = (TextView)findViewById(R.id.myscore_text);
        mNameEntry = (EditText)findViewById(R.id.highScoreName);
        mCongratsText = (TextView)findViewById(R.id.congrats_text);
        mSaveButton = (Button)findViewById(R.id.save_button);

        mPoints = getIntent().getIntExtra(EXTRA_POINTS_DONE, -1 );

        //Log.v("MEMEMEMEME", ""+mPoints);

        //TODO This is setting the highscore every time no matter what.  Needs fixing.
        //myRef.setValue("" + mPoints);
        //String hScoreText = QuizPreferences.getStoredScore(getApplicationContext());
        //String hScoreName = QuizPreferences.getStoredName(getApplicationContext());

        String hScoreText = null;
        String hScoreName = null;

        int high_score = 0;
        if (hScoreText != null)
           high_score = Integer.parseInt(hScoreText);


        mScoreText.setText(mScoreText.getText().toString() + " " + mPoints +
                ", high score was " + high_score + " by " + hScoreName);

        if (high_score < 1 || high_score < mPoints)
        {
            //new high score!
            mNameEntry.setVisibility(View.VISIBLE);
            mCongratsText.setVisibility(View.VISIBLE);
            mSaveButton.setVisibility(View.VISIBLE);
        }
    }

    /*
    public void load(View view)
    {   mLeaderAdapter.clear();

        for(LeaderEntry entry: leaderList)
        {
            mLeaderAdapter.add(entry);
        }
    }
    */

    public static Intent newIntent(Context packageContext, int points)
    {
        Intent intent = new Intent(packageContext, FinishActivity.class);
        intent.putExtra(EXTRA_POINTS_DONE, points);
        return intent;
    }

    public void saveClick(View view) {
        int msg = R.string.saved_message;
        if (mNameEntry.getText().toString().length() > 0) {
            LeaderEntry newScore = new LeaderEntry(mNameEntry.getText().toString(), mPoints);
            myRef.child(myRef.push().getKey()).setValue(newScore);
            leaderList.add(newScore);
            mLeaderAdapter.updateList(leaderList);
        }
        else
            msg = R.string.not_saved_message;

        //QuizPreferences.setStoredScore(getApplicationContext(), "" + mPoints);
        //QuizPreferences.setStoredName(getApplicationContext(), "" + mNameEntry.getText());
        mCongratsText.setText(msg);
        mLeaderAdapter.notifyItemInserted(leaderList.size()-1);
    }
}
