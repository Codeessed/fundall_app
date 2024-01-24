package com.android.fundallapp.auth.data.localdata

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

class Authpreference {

    companion object{
        val MY_PREF = "my_pref"
        val AUTH_KEY = "auth_key"


        lateinit var preferences: SharedPreferences

        fun initSharedPreference(activity: Activity){
            preferences = activity.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE)
        }

        fun <T> put(`object`: T, key: String) {
            val jsonString = GsonBuilder().create().toJson(`object`)
            preferences.edit().putString(key, jsonString).apply()
        }

        /**
         * Used to retrieve object from the Preferences.
         *
         * @param key Shared Preference key with which object was saved.
         **/
        inline fun <reified T> get(key: String): T? {
            val value = preferences.getString(key, null)
            return GsonBuilder().create().fromJson(value, T::class.java)
        }

    }
}