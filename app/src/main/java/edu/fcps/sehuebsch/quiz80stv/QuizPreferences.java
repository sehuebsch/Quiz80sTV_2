package edu.fcps.sehuebsch.quiz80stv;

import android.content.Context;
import android.preference.PreferenceManager;

public class QuizPreferences {

    private static final String PREF_SEARCH_HIGHSCORE = "searchHighScore";
    private static final String PREF_SEARCH_SCORENAME = "searchHighName";

    public static String getStoredScore(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_SEARCH_HIGHSCORE, null);
    }

    public static void setStoredScore(Context context, String highscore)
    {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_SEARCH_HIGHSCORE, highscore)
                .apply();
    }

    public static String getStoredName(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_SEARCH_SCORENAME, null);
    }

    public static void setStoredName(Context context, String highname)
    {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_SEARCH_SCORENAME, highname)
                .apply();
    }
}
