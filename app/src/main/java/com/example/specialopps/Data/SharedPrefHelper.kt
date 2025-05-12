
package com.example.specialopps.Data

import android.content.Context
import com.example.specialopps.Models.StudentProfile
import com.google.gson.Gson

object SharedPrefHelper {
    private const val PREF_NAME = "student_profile_pref"
    private const val KEY_PROFILE = "profile_data"

    fun saveProfile(context: Context, profile: StudentProfile) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = Gson().toJson(profile)
        sharedPref.edit().putString(KEY_PROFILE, json).apply()
    }

    fun loadProfile(context: Context): StudentProfile? {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = sharedPref.getString(KEY_PROFILE, null)
        return json?.let { Gson().fromJson(it, StudentProfile::class.java) }
    }
}
