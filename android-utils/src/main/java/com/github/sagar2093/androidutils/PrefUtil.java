package com.github.sagar2093.androidutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Sagar Chapagain on 9/17/2017.
 * https://github.com/sagar2093
 * sagar2093@gmail.com
 */

public class PrefUtil {

    // Shared Preferences
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    // Shared preferences file name and shared pref mode
    //private static final String PREF_NAME = "gunaso_pref";
    //private int PRIVATE_MODE = 0;

    private PrefUtil() {
    }

    /* for default usage
     * better if this constructor is initialized on base activity/fragment
     */
    public PrefUtil(Context context, String PREF_NAME, int MODE) {
        pref = context.getSharedPreferences(PREF_NAME, MODE); //private mode = 0
        editor = pref.edit();
    }

    public int getInt(String key, int defaultValue) {
        return pref.getInt(key, defaultValue);
    }

    public void putString(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public String getString(String key, String defaultValue) {
        return pref.getString(key, defaultValue);
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return pref.getBoolean(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void remove(String key) {
        editor.remove(key).commit();
    }

    /*default shared preferences*/
    public static int getInt(Context context, final String key, int defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt(key, defaultValue);
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(key, value).apply();
    }

    public static String getString(Context context, final String key, final String defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(key, defaultValue);
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key, final boolean defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(key, defaultValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean(key, value).apply();
    }

    public static void remove(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().remove(key).apply();
    }
}