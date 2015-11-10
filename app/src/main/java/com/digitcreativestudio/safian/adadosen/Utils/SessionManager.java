package com.digitcreativestudio.safian.adadosen.Utils;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.digitcreativestudio.safian.adadosen.LoginActivity;

import java.util.HashMap;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AdaDosenPref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_ID = "id";

    public static final String KEY_NIM = "name";

    public static final String KEY_NAME = "name";

    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String id, String nim, String name){
        // Storing login value as TRUE
        String[] nameArray = name.split("\\s+");
        String newName = "";
        for(int i = 0; i < nameArray.length; i++){
            if(i>1){
                newName += " "+nameArray[i].charAt(0)+".";
            }else{
                newName += " "+nameArray[i];
            }
        }
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_ID, id);

        editor.putString(KEY_NIM, nim);

        editor.putString(KEY_NAME, newName);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<>();
        // user name
        user.put(KEY_ID, pref.getString(KEY_ID, null));
        user.put(KEY_NIM, pref.getString(KEY_NIM, null));
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
/*
        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);*/
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void setRegistered(boolean registered){
        editor.remove(SENT_TOKEN_TO_SERVER);
        editor.putBoolean(SENT_TOKEN_TO_SERVER, registered);
        editor.commit();
    }
}
