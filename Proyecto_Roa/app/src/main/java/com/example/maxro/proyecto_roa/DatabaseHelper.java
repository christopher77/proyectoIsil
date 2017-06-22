package com.example.maxro.proyecto_roa;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by maxro on 20/06/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;

    static String DATABASE_NAME="ProyectoBD";

    public static final String TABLE_NAME="UserTable";

    public static final String Table_Column_1_User="user";
    public static final String Table_Column_2_Password="password";

    private static final String MYNOTES_PREFERENCES = "mynotesPreferences";
    private static final String PREFERENCES_USERNAME = MYNOTES_PREFERENCES + ".username";
    private static final String PREFERENCES_PASSWORD = MYNOTES_PREFERENCES + ".password1";

    public static void saveSession(Context context,String username,String password)
    {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(PREFERENCES_USERNAME, username);
        editor.putString(PREFERENCES_PASSWORD, password);
        editor.apply();
    }

    public static void signOut(Context context) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.remove(PREFERENCES_USERNAME);
        editor.remove(PREFERENCES_PASSWORD);
        editor.apply();
    }

    public static String getUserSession(Context context)
    {
        SharedPreferences sharedPreferences= getSharedPreferences(context);
        String username= sharedPreferences.getString(PREFERENCES_USERNAME,null);

        return username;
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.edit();
    }

    public static boolean isSignedIn(Context context) {
        final SharedPreferences preferences = getSharedPreferences(context);
        return preferences.contains(PREFERENCES_USERNAME) &&
                preferences.contains(PREFERENCES_PASSWORD);
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(MYNOTES_PREFERENCES, Context.MODE_PRIVATE);
    }

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql= "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_1_User+"" +
                " VARCHAR, "+Table_Column_2_Password+" VARCHAR)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
