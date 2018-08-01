package edu.fcps.sehuebsch.quiz80stv;

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private int mHintResId;
    private int mImgResId;

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public int getHintResId() {
        return mHintResId;
    }

    public void setHintResId(int textResId) {
        mHintResId = textResId;
    }

    public int getImgResId() {
        return mImgResId;
    }

    public void setImgResId(int imgResId) {
        mImgResId = imgResId;
    }

    public Question(int textResId, boolean answerTrue, int hintResId, int imgResId)
    {
        mTextResId = textResId;
        mHintResId = hintResId;
        mImgResId = imgResId;
        mAnswerTrue = answerTrue;
    }

}
