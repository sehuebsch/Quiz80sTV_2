package edu.fcps.sehuebsch.quiz80stv;

public class LeaderEntry {
    private String mLeaderName;
    private int mHighScore;

    public String getLeaderName() {
        return mLeaderName;
    }

    public void setLeaderName(String leaderName) {
        mLeaderName = leaderName;
    }

    public int getHighScore() {
        return mHighScore;
    }

    public void setHighScore(int highScore) {
        mHighScore = highScore;
    }

    public LeaderEntry()
    {
        mLeaderName = "";
        mHighScore = 0;
    }

    public LeaderEntry(String leaderName, int highScore) {
        mLeaderName = leaderName;
        mHighScore = highScore;
    }

    public String toString()
    {
        return mLeaderName + " Score: " + mHighScore;
    }
}