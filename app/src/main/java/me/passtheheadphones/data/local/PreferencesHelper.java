package me.passtheheadphones.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.passtheheadphones.injection.ApplicationContext;

@Singleton
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "android_boilerplate_pref_file";
    public static final String PREF_AUTH_KEY = "pref_auth_key";
    public static final String PREF_USER_ID_KEY = "pref_user_id_key";


    private final SharedPreferences mPref;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public void setAuth(String auth) {
        mPref.edit().putString(PREF_AUTH_KEY, auth).apply();
    }

    public String getAuth() {
        return mPref.getString(PREF_AUTH_KEY, "");
    }

    public void setUserId(int id) {
        mPref.edit().putInt(PREF_USER_ID_KEY, id).apply();
    }

    public int getUserId() {
        return mPref.getInt(PREF_USER_ID_KEY, 0);
    }
}
