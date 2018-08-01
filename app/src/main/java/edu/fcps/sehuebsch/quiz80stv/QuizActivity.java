package edu.fcps.sehuebsch.quiz80stv;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_HINT=0;
    private static final int POINTS_CORRECT_NO_HINT = 20;
    private static final int POINTS_CORRECT_HINT = 15;
    private static final int POINTS_INCORRECT = -5;

    private TextView mPointsTextView;
    private TextView mQuestionTextView;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mHintButton;
    private ImageView mQuestionImage;

    private int mPoints = 0;
    private boolean mHintUsed = false;

    private Question[] mQuestionBank = new Question[] {
           new Question(R.string.question1, false, R.string.hint1, R.drawable.fonzie),
           new Question(R.string.question2, true, R.string.hint2, R.drawable.richiejoanie),
           new Question(R.string.question3, false, R.string.hint3, R.drawable.laverneshirley),
           new Question(R.string.question4, true, R.string.hint4, R.drawable.jrewing)
    };

    private int mCurrIdx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mPointsTextView = (TextView)findViewById(R.id.points_text_view);
        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mNextButton = (Button)findViewById(R.id.next_button);
        mHintButton = (Button)findViewById(R.id.hint_button);
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mQuestionImage = (ImageView)findViewById(R.id.image_question_view);

        int imageNum = mQuestionBank[mCurrIdx].getImgResId();
        mQuestionImage.setImageResource(imageNum);

        int questionNum = mQuestionBank[mCurrIdx].getTextResId();
        mQuestionTextView.setText(questionNum);
        mPointsTextView.setText("Score: " + mPoints);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrIdx++;
                mCurrIdx %= mQuestionBank.length;
                int questionNum = mQuestionBank[mCurrIdx].getTextResId();
                mQuestionTextView.setText(questionNum);
                int imageNum = mQuestionBank[mCurrIdx].getImgResId();
                mQuestionImage.setImageResource(imageNum);
                mHintUsed = false;
            }
        });

        /*
         * We are using an alternative method here - defining in the activity_quiz.xml what
         * method should be called when hintButton is clicked, then putting the method
         * below.
         *
        mHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizActivity.this, HintActivity.class);
                startActivity(intent);
            }
        });
        */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode != Activity.RESULT_OK)
        {
            return;
        }

        if (requestCode == REQUEST_CODE_HINT)
        {
            if (data == null)
            {
                return;
            }
            mHintUsed = HintActivity.wasAnswerShown(data);
        }
    }

    public void handleAnswer(boolean theySaid)
    {
        int msg = 0;
        boolean answer = mQuestionBank[mCurrIdx].isAnswerTrue();

        if (answer == theySaid) {
            msg = R.string.correct_toast;
            if (mHintUsed)
            {
                msg = R.string.hinted_toast;
                mPoints += POINTS_CORRECT_HINT;
            }
            else
                mPoints += POINTS_CORRECT_NO_HINT;
        }
        else {
            msg = R.string.incorrect_toast;
            mPoints += POINTS_INCORRECT;
        }

        Toast.makeText(QuizActivity.this,
                msg,
                Toast.LENGTH_SHORT).show();

        mPointsTextView.setText("Score: " + mPoints);

    }

    public void hintClick(View view) {
        //Intent intent = new Intent(this, HintActivity.class);
        boolean answerIsTrue = mQuestionBank[mCurrIdx].isAnswerTrue();
        int hintId = mQuestionBank[mCurrIdx].getHintResId();
        Intent intent = HintActivity.newIntent(this, answerIsTrue, hintId);
        //startActivity(intent);
        startActivityForResult(intent, REQUEST_CODE_HINT);
    }

    public void doneClick(View view) {
        Intent intent = FinishActivity.newIntent(this, mPoints);
        startActivity(intent);
    }
}
