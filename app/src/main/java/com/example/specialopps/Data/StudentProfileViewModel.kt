package com.example.specialopps.Data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.specialopps.Models.StudentProfile
import com.example.specialopps.Network.RetrofitInstance
import kotlinx.coroutines.launch

class StudentProfileViewModel : ViewModel() {

    fun saveProfile(
        profile: StudentProfile,
        context: Context,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.saveStudentProfile(profile)
                if (response.isSuccessful) {
                    SharedPrefHelper.saveProfile(context, profile) // Save locally
                    onSuccess()
                } else {
                    onError("Server Error: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                onError("Network Error: ${e.message}")
            }
        }
    }
}
