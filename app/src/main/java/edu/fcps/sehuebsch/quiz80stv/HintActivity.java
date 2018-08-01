package edu.fcps.sehuebsch.quiz80stv;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {

    /* In her code, Ria just put a String literal for this step (I got this from the textbook)
     * It doesn't matter, it is used as a label for the extra so you can obtain the value on
     * the other side.  She put "HINT" instead of this constant in both places where it is used.
     * (She skipped the newIntent method initially so the other side could putExtra directly.
     */
    private static final String EXTRA_ANSWER_IS_TRUE =
            "edu.fcps.sehuebsch.quiz80stv.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN =
            "edu.fcps.sehuebsch.quiz80stv.answer_shown";
    private static final String EXTRA_HINT_TEXT =
            "edu.fcps.sehuebsch.quiz80stv.hint_text";
    private Button mShowButton;
    private boolean mAnswerIsTrue;
    private int mHintId;
    private TextView mHintText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        mShowButton = (Button)findViewById(R.id.show_button);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mHintText = (TextView)findViewById(R.id.hint_text);
        mHintId = getIntent().getIntExtra(EXTRA_HINT_TEXT, -1);



        mShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHintText.setText(mHintId);
                setAnswerShownResult(true);
            }
        });
    }

    public static Intent newIntent(Context packageContext, boolean answerIsTrue, int hintResId)
    {
        Intent intent = new Intent(packageContext, HintActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        intent.putExtra(EXTRA_HINT_TEXT, hintResId);
        return intent;
    }

    private void setAnswerShownResult(boolean isAnswerShown)
    {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    public static boolean wasAnswerShown(Intent result)
    {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }
}
