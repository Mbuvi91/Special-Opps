package com.example.specialopps.Models

import com.google.gson.annotations.SerializedName

data class StudentProfile(
    @SerializedName("name")
    val name: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("course")
    val course: String,

    @SerializedName("imageUri")
    val imageUri: String? = null
)
